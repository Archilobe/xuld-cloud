package com.evax.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Lionel
 * @Description: 授权过滤器
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("授权请求............");
        //拦截的逻辑。根据具体业务逻辑做拦截。
//        String perm = exchange.getRequest().getQueryParams().getFirst("perm");
//        if (perm == null || perm.isEmpty()) {
//            return Mono.defer(() -> {
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);//设置status
//                final ServerHttpResponse response = exchange.getResponse();
//                ResultJson resultJson = ResultJson.failure(ResultCode.FORBIDDEN);
//                byte[] bytes = JSONObject.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8);
//                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                logger.info("AuthorizationFilter拦截非法请求，没有检测到权限码perm............");
//                return response.writeWith(Flux.just(buffer));
//            });
//        }
        logger.info("授权请求通过............");
        return chain.filter(exchange);
    }
}