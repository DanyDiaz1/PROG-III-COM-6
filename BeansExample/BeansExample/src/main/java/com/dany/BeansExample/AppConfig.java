package com.dany.BeansExample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Version version(){
        return new Version(1,0,0);
    }




}
