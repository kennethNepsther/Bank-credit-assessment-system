package it.nepstherti.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCloudgatewayApplication.class, args);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route( r -> r.path("/clients/**").uri("lb://ms-clients"))
				.route( r -> r.path("/cards/**").uri("lb://ms-cards"))
				.route( r -> r.path("/credit-assessment/**").uri("lb://ms-credit-assessment"))
				.build();

	}

}
