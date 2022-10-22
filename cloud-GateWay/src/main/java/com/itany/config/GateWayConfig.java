package com.itany.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
   public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routs =   routeLocatorBuilder.routes();
        routs.route("path_clouduser",
                p->p.path("/test/**")
                        .uri("http://localhost:9002")).build();
        return  routs.build();
   }
}
