package com.example.consulservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "my", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class MyProperties {
	
    private String prop;

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}
    
}