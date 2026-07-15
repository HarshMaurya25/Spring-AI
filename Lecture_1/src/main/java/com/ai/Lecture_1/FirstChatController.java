package com.ai.Lecture_1;


import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public class FirstChatController {

    private final ChatClient chatClient;

    private FirstChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> getFirstChat(
            @RequestParam(value = "q" , required = true) String request
    ){

        var response = chatClient.prompt(request).call().content();
        return ResponseEntity.ok(response);
    }

}
