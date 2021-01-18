package com.shanxijuzhi.juzhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages = {"com.shanxijuzhi.juzhi.model"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PtApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtApplication.class, args);
    }

}
