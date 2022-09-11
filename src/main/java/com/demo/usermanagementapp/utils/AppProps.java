package com.demo.usermanagementapp.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppProps {
	
	private Map<String, String> messages = new HashMap<String,String>();

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "AppProps [messages=" + messages + "]";
	}
	

}
