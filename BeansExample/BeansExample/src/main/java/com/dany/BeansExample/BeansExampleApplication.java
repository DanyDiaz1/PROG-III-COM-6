package com.dany.BeansExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeansExampleApplication {

	public static void main(String[] args) {
		ApplicationContext apc = SpringApplication.run(BeansExampleApplication.class, args);
		for (String beans : apc.getBeanDefinitionNames()){
			System.out.println();
			System.out.println(beans);
		}
	}

}
