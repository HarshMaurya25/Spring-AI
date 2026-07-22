package com.ai.Lecture_1.advisior;

import com.ai.Lecture_1.advisior.service.AdvisiorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/advisior")
public class Controller {

    private final AdvisiorService advisiorService;


    @GetMapping("/ask")
    public ResponseEntity<String> asking(
            @RequestParam(value = "q") String query
    ){
        return ResponseEntity.ok(advisiorService.ask(query));
    }


}
