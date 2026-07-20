package com.ai.Lecture_1.promptingAndParsing.service;

import com.ai.Lecture_1.promptingAndParsing.entity.PersonEntity;

public interface ChatClientService {

    String chat(String query);

    String chatWithPrompt(String query);

    String chatUsingResource(String query);

    PersonEntity chatWithStructureOutput(String query);

}
