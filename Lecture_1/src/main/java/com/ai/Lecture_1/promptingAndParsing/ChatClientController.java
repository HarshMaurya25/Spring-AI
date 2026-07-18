package com.ai.Lecture_1.promptingAndParsing;

import com.ai.Lecture_1.promptingAndParsing.service.ChatClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ChatClientController {

    private ChatClientService chatClientService;

    @GetMapping("/question")
    public ResponseEntity<?> askQuestion(
            @RequestParam(
                    value = "ask" , required = true
            ) String query
    ){
        return ResponseEntity.ok(chatClientService.chat(query));
    }

    @GetMapping("/promptObj/question")
    public ResponseEntity<?> askQuestionWithPromptObject(
            @RequestParam(
                    value = "ask" , required = true
            ) String query
    ){
        return ResponseEntity.ok(chatClientService.chatWithPrompt(query));
    }

    @GetMapping("/resource/question")
    public ResponseEntity<?> askQuestionWithResource(
            @RequestParam(
                    value = "ask" , required = true
            ) String query
    ){
        return ResponseEntity.ok(chatClientService.chatWithPrompt(query));
    }
}
