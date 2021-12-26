/**
 * 
 */
package com.hcl.ms.core.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.hcl.ms.core.logs.LoggerIntercepter;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author santosh.kushwah
 *
 */

@Configuration
@EnableWebMvc
public class MsBaseConfiguration implements WebMvcConfigurer {

	@Autowired
	private LoggerIntercepter logInterceptor;

	
	public static GroupedOpenApi adminApi(String groupName,String path) {
	      return GroupedOpenApi.builder()
	              .group(groupName)
	              .pathsToMatch(path+"**")
	              .build();
	  }
	
	public static OpenAPI springShopOpenAPI(String title,String desc,String version,String licenceName, String url, String appUrl) {
		return new OpenAPI()
				.info(new Info().title(title).description(desc)
						.version(version).license(new License().name(licenceName).url(url)))
				.externalDocs(new ExternalDocumentation()
						.url(appUrl));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(logInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
	}

}
