package br.com.urbainski.microservices.queue.listeners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableDiscoveryClient
@ConfigurationPropertiesScan
@EnableJms
public class QueueListenersApplication {

    public static void main(String[] args) {

        SpringApplication.run(QueueListenersApplication.class, args);
    }

}