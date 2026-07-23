package com.ai.Lecture_1.stream;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StreamService {

    private final ChatClient chatClient;

    public StreamService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public Flux<String> streamAsk(String query){
        return chatClient.prompt(query)
                .stream()
                .content();
    }
}
