package com.trlogic.wlagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.trlogic.wlagent.domain.Message;
import com.trlogic.wlagent.service.InMemoryMessageRepository;
import com.trlogic.wlagent.service.MessageRepository;

@SpringBootApplication
public class MainApplication {
	
	@Bean
	public MessageRepository messageRepository() {
		return new InMemoryMessageRepository();
	}

	@Bean
	public Converter<String, Message> messageConverter() {
		return new Converter<String, Message>() {
			@Override
			public Message convert(String id) {
				return messageRepository().findMessage(Long.valueOf(id));
			}
		};
	}
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
