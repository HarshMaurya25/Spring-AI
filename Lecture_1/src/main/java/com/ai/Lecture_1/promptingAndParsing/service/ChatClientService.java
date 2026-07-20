package com.ai.Lecture_1.promptingAndParsing.service;

import com.ai.Lecture_1.promptingAndParsing.entity.PersonEntity;

import java.util.List;

public interface ChatClientService {

    String chat(String query);

    String chatWithPrompt(String query);

    String chatUsingResource(String query);

    List<PersonEntity> chatWithStructureOutput(String query);

}
