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

//      SafeGuardAdvisor is a Spring AI advisor that checks a prompt against a safety policy before it reaches the language model.
//      If the prompt is considered unsafe, it returns a failure response instead of calling the model.
        SafeGuardAdvisor safeGuardAdvisor = SafeGuardAdvisor
                .builder()
                .sensitiveWords(List.of("game" , "games" , "pokemon" , "megas"))
                .failureResponse("Unable to Response due to private policy") // Message if sensitive word is found
                .build();

        // If massage is not set then it will use default message

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor() , safeGuardAdvisor)
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
