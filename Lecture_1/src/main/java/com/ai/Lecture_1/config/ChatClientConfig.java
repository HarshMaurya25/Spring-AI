package com.ai.Lecture_1.config;

import com.openai.models.ChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean(name = "OpenAI")
    public ChatClient openAiChatClient(OpenAiChatModel chatModel){
        return ChatClient.builder(chatModel).build();
    }

}
