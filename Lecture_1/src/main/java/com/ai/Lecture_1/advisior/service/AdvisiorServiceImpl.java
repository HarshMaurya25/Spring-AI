package com.ai.Lecture_1.advisior.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisiorServiceImpl implements AdvisiorService {

//    Request :- Spring AI -> Advisior -> LLM
//    Response :- LLM -> Advisior -> Spring AI

    private final ChatClient chatClient;

    public AdvisiorServiceImpl(ChatClient.Builder builder) {

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


    @Override
    public String ask(String query) {
        return chatClient
                .prompt(query)
                .call()
                .content();
    }
}
