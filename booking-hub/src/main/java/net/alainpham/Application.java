package net.alainpham;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import org.apache.camel.opentelemetry.starter.CamelOpenTelemetry;


@SpringBootApplication
@EnableWebSocket
@CamelOpenTelemetry
@EnableScheduling
public class Application implements WebSocketConfigurer{

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		// Expose endpoint and add Handler. Wildcard allowed origins to support COORS
		// registry.addHandler(new WebsocketTextHandler(), "/websocket").setAllowedOrigins("*");
		registry.addHandler(new WebSocketTextHandler(), "/websocket");
	}
}