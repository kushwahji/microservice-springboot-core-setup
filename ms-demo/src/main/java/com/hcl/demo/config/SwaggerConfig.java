/**
 * 
 */
package com.hcl.demo.config;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hcl.ms.core.config.MsBaseConfiguration;
import io.swagger.v3.oas.models.OpenAPI;



/**
 * @author santosh.kushwah
 *
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi adminApi() {
		return MsBaseConfiguration.adminApi("ms-demo", "/api/");
		
	}
	@Bean
	public OpenAPI springShopOpenAPI() {
		return MsBaseConfiguration.springShopOpenAPI("Demo", "Demo miroservice & spring boot for training", "1.0.0", "Demo", "https://www.hcl.com", "http://localhost:2011");
	}
}
