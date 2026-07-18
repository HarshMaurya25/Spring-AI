package com.ai.Lecture_1.promptingAndParsing.service;

public interface ChatClientService {

    String chat(String query);

    String chatWithPrompt(String query);

    String chatUsingResource(String query);

}
