package com.ai.Lecture_1;


import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Content;
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

    @GetMapping("/ask")
    public ResponseEntity<String> askQuestion(
            @RequestParam(value = "ask" , required = true) String request
    ){
        Prompt prompt = Prompt
                .builder()
                .messages(
                        new UserMessage(request),
                        new SystemMessage("Act as the Smart and genius spokesperson of the media company")
                )
                .build();

        var response = chatClient.prompt(prompt).call().content();

        return ResponseEntity.ok().body(response);
    }

}
