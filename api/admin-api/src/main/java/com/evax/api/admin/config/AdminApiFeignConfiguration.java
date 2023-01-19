package com.evax.api.admin.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.evax.api.admin.api")
public class AdminApiFeignConfiguration {

}