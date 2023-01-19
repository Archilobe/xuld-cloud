package com.evax.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import com.evax.common.utils.JwtUtil;
import com.evax.gateway.filter.allow.FilterProperties;
import com.evax.redis.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Lionel
 * @Description: 认证过滤器
 */
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    private FilterProperties filterProperties;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("认证请求............");
        String url = exchange.getRequest().getURI().getPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<String> paths = filterProperties.getPaths();
        for (String path : paths) {
            if (antPathMatcher.match(path, url)) {
                logger.info("认证请求通过............");
                return chain.filter(exchange);
            }
        }
        String bearerToken = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return Mono.defer(() -> {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                final ServerHttpResponse response = exchange.getResponse();
                ResultJson resultJson = ResultJson.failure(ResultCode.UNAUTHORIZED);
                byte[] bytes = JSONObject.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                logger.info("认证请求拦截，没有检测到token............");
                return response.writeWith(Flux.just(buffer));
            });
        } else {
            String token = bearerToken.substring("Bearer ".length());
            if (!redisUtils.hasKey(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                ResultJson resultJson = ResultJson.failure(ResultCode.TOKEN_EXPIRED);
                byte[] bytes = JSONObject.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                final ServerHttpResponse response = exchange.getResponse();
                logger.info("认证请求拦截，token无效............");
                return response.writeWith(Flux.just(buffer));
            }
        }
        logger.info("认证请求通过............");
        return chain.filter(addInfoToRequest(exchange));
    }

    /**
     * 将用户信息载入request
     *
     * @param exchange
     * @return
     */
    private ServerWebExchange addInfoToRequest(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        URI uri = exchange.getRequest().getURI();
        String originalQuery = uri.getRawQuery();
        StringBuilder query = new StringBuilder();
        if (org.springframework.util.StringUtils.hasText(originalQuery)) {
            query.append(originalQuery);
            if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                query.append('&');
            }
        }
        String token = headers.getFirst("token").substring("Bearer ".length());
        String loginId = JwtUtil.getUserId(token);
        query.append("loginId=").append(loginId);
        URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).build(true).toUri();
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().uri(newUri).build();
        return exchange.mutate().request(serverHttpRequest).build();
    }

    /**
     * 将用户信息载入request
     *
     * @param exchange
     * @param loginId  当前登录用户ID
     * @param orgIds   当前用户当前组织
     * @return
     */
    private ServerWebExchange addInfoToRequest(ServerWebExchange exchange, String loginId, String orgIds) {
        URI uri = exchange.getRequest().getURI();
        StringBuilder query = new StringBuilder();
        String originalQuery = uri.getRawQuery();

        if (org.springframework.util.StringUtils.hasText(originalQuery)) {
            query.append(originalQuery);
            if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                query.append('&');
            }
        }
        query.append("loginId=").append(loginId);
        URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).build(true).toUri();
        // 将用户ID，用户默认组织的ID放入header
        // 将用户ID放入参数
        String currentOrgId = orgIds.split(",")[0];
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header("_current_id_user", loginId)
                .header("_current_id_org", currentOrgId).uri(newUri).build();
        return exchange.mutate().request(serverHttpRequest).build();
    }

    private Map getAllParamtersRequest(ServerHttpRequest request) {
        Map map = new HashMap();
        MultiValueMap<String, String> paramNames = request.getQueryParams();
        Iterator it = paramNames.keySet().iterator();
        while (it.hasNext()) {
            String paramName = (String) it.next();

            List<String> paramValues = paramNames.get(paramName);
            if (paramValues.size() >= 1) {
                String paramValue = paramValues.get(0);
                map.put(paramName, paramValue);
            }
        }
        return map;
    }

    private Map getAllHeadersRequest(ServerHttpRequest request) {
        Map map = new HashMap();
        HttpHeaders hearders = request.getHeaders();
        Iterator it = hearders.keySet().iterator();
        while (it.hasNext()) {
            String keyName = (String) it.next();
            List<String> headValues = hearders.get(keyName);
            if (headValues.size() >= 1) {
                String kvalue = headValues.get(0);
                map.put(keyName, kvalue);
            }
        }
        return map;
    }
}