package com.backend;

import javax.servlet.ServletException;

import com.backend.core.EmbeddedServer;
import com.backend.resource.ResourceFactory;

public class MyApplication {
// url http://localhost:8080/app-name/api/backend/4
    
	public static void main(String[] args) throws ServletException {
		
		new EmbeddedServer("localhost", 8080)
			.contextPath("/app-name")
			.deploymentName("app-name")
			.appPath("/api")
			.resourcesClass(ResourceFactory.class)
			.start();
	}
	
}
