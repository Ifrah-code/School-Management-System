package project.com.cognifyz.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final HandlerInterceptor loggingInterceptor;
	
	@Autowired
	public WebConfig(LoggingInterceptor loggingInterceptor) {
		this.loggingInterceptor = (HandlerInterceptor) loggingInterceptor;
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor);
	}

}
