package com.ai.Lecture_1.MultiModel;


import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public class FirstChatController {

    private final ChatClient chatClient;

//    Chat-client by the use ChatClient Builder

//    private FirstChatController(ChatClient.Builder builder){
//        this.chatClient = builder.build();
//    }

//    We can simply add the custom chat-client for the service by using the Chat Model
//    Make sure spring.ai.chat.client.enabled = false
//    2 method to do it First is this and second is the config method in config folder

//    private FirstChatController(OpenAiChatModel chatModel){
//        this.chatClient = ChatClient.builder(chatModel).build();
//    }

//    Using config to load to the chat client
    public FirstChatController(@Qualifier("OpenAI") ChatClient chatClient) {
        this.chatClient = chatClient;
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
