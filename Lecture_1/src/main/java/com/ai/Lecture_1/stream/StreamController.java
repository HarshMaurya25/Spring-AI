package com.ai.Lecture_1.stream;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("/Stream")
public class StreamController {

    private final StreamService service;

    @GetMapping("/ask")
    public ResponseEntity<Flux<String>> ask(
            @RequestParam(value = "q") String query
    ){
        return ResponseEntity.ok(service.streamAsk(query));
    }

}
