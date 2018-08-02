package com.fromqtoj.config;

import com.fromqtoj.filter.RequestLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean requestLogFilter() {
		final FilterRegistrationBean fBean = new FilterRegistrationBean();
		final RequestLogFilter filter = new RequestLogFilter();
		fBean.setFilter(filter);
		return fBean;
	}
	

}
