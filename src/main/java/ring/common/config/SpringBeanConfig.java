package ring.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ring.common.interceptors.SpringContextHolder;

@Configuration
public class SpringBeanConfig {
	
	@Bean
	public SpringContextHolder springContextHolder(){
		return new SpringContextHolder();
	}
}
