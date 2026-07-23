package com.ai.Lecture_1.advisior.service;

import com.ai.Lecture_1.advisior.custom.CustomAdvisorGiveToken;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisiorServiceImpl implements AdvisiorService {

//    Request :- Spring AI -> Advisior -> LLM
//    Response :- LLM -> Advisior -> Spring AI

    private final ChatClient chatClient;

    public AdvisiorServiceImpl(ChatClient.Builder builder) {

//      MessageChatMemoryAdvisor --------------------------------------------------------------------------------------------

//      MessageChatMemoryAdvisor is the primary Spring AI advisor for maintaining conversational context.
//      It automatically loads previous messages into each prompt and stores the latest user/assistant messages after the model responds.

        InMemoryChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();

        ChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();


//      SafeGuard Advisor-------------------------------------------------------------------------------------------------

//      SafeGuardAdvisor is a Spring AI advisor that checks a prompt against a safety policy before it reaches the language model.
//      If the prompt is considered unsafe, it returns a failure response instead of calling the model.
        SafeGuardAdvisor safeGuardAdvisor = SafeGuardAdvisor
                .builder()
                .sensitiveWords(List.of("game" , "games" , "pokemon" , "megas"))
                .failureResponse("Unable to Response due to private policy") // Message if sensitive word is found
                .build();

        // If massage is not set then it will use default message

        this.chatClient = builder
//                .defaultAdvisors(new SimpleLoggerAdvisor() , safeGuardAdvisor , messageChatMemoryAdvisor)
                .defaultAdvisors(new CustomAdvisorGiveToken())
                .build();
    }


    @Override
    public String ask(String query , String id) {
        return chatClient
                .prompt(query)
                .advisors(
                        a-> a.param(ChatMemory.CONVERSATION_ID , id)
                )
                .call()
                .content();
    }
}
