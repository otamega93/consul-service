package com.example.consulservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ConsulServiceApplication.class, args);
	}
	
	@RestController
	public class DiscoveryClientController {
	  
	    @Autowired
	    private DiscoveryClient discoveryClient;
	    
	    @Autowired
	    private MyProperties myProperties;
	    
	    @Autowired
	    private ConfigurableApplicationContext applicationContext;
	 
	    public Optional<URI> serviceUrl() {
	        return discoveryClient.getInstances("consul-service")
	          .stream()
	          .map(si -> si.getUri())
	          .findFirst();
	    }
	     
	    @GetMapping("/ping")
	    public String ping() {
	    	
	    	List<ServiceInstance> instances = discoveryClient.getInstances("consul-service");
	    	for (ServiceInstance serviceInstance : instances) {
				System.out.println(serviceInstance.toString());
			}
	        return "pong";
	    }
	    
	    // To refresh the value, send a POST to http://host:port/actuator/refresh
	    @GetMapping("/propWithRefresh")
	    public ResponseEntity<String> propertyRefreshable() {
	    	
	    	return new ResponseEntity<String>(myProperties.getProp(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/customShutdown")
	    public ResponseEntity<?> shutdown() {
	    	
	    	applicationContext.stop();
	    	applicationContext.close();
	    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    }
	    
	}
}
