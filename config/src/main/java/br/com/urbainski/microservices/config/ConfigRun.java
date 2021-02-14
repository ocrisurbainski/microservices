package br.com.urbainski.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication(exclude={SolrAutoConfiguration.class})
@EnableConfigServer
public class ConfigRun {

    public static void main(String[] args) {

        SpringApplication.run(ConfigRun.class, args);
    }

}