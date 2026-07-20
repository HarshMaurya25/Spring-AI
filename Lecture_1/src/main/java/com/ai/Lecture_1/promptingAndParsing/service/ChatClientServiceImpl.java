package com.ai.Lecture_1.promptingAndParsing.service;

import com.ai.Lecture_1.promptingAndParsing.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class ChatClientServiceImpl implements ChatClientService{

    private  final ChatClient chatClient;

    public ChatClientServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

//        There are mainly 3 Main type of the sending prompt
//        1. Send prompt inside the prompt :  chatclient.prompt( <query> )
//        2. Send by using the .user() and .system() function
//        3. Create the object for the Prompt and pass that

    @Override
    public String chat(String query) {

        return chatClient.prompt()
                .user(query)   // ---> This handle the user query that user want to ask
                .system("You are expert in the football")  // ---> This handle the System behaviour in response
                .call()
                .content();
    }


//    Prompt Object -------------------------------------------------------------------------------------
//    Prompt Object include
//        1. List of Message
//        2. ChatOption

//    Type of Message :
//        1. USER – The user's question or request.
//        2. SYSTEM – Instructions that define the AI's behavior.
//        3. ASSISTANT – The AI's previous response, used to maintain conversation context.
//        4. TOOL – The result returned from an external tool or function called by the AI.

    @Override
    public String chatWithPrompt(String query) {

        PromptTemplate template = PromptTemplate
                .builder()
                .template("Explain this concept {placeholder}")
                .build();


//        Prompt prompt = new Prompt(query);

        Prompt prompt = template.create(
                Map.of("placeholder" , query))
                .augmentSystemMessage("Behave like a teacher of AI");

        return  chatClient
                .prompt(prompt)
                .call()
                .content();
    }


//    Resource For the Prompt --------------------------------------------------------------------
//    It Should Store in the .st folder

    @Value("src/main/resources/static/learning.st")
    Resource promptResourse;

    @Override
    public String chatUsingResource(String query) {

        PromptTemplate template = PromptTemplate
                .builder()
                .renderer(
                        StTemplateRenderer.builder()
                                .startDelimiterToken('<')
                                .endDelimiterToken('>')
                                .build()  //--> We can add the custom Placeholder
                )
                .resource(promptResourse)
                .build();

        Prompt prompt = template
                .create(
                        Map.of("PlaceHolder", query)
                )
                .augmentSystemMessage("You are expert in AI");

        return chatClient.prompt(prompt).call().content();
    }

//    Good Prompting :
//      1. Instructions – what to do.
//      2. Context – background information.
//      3. User input – the actual request.
//      4. Output format – how the answer should look.

//    Structure Output -----------------------------------------------------------------------------

    @Override
    public List<PersonEntity> chatWithStructureOutput(String query) {
//        ChatResponse chatResponse = chatClient
//                .prompt(query + " Under 20 words")
//                .call()
//                .chatResponse();
//
//        assert chatResponse != null;
//        log.info(chatResponse
//                .getMetadata()  // ----> Get the mata-data
//                .getUsage()
//                .toString()
//        );
//
//        return Objects.requireNonNull(chatResponse.getResult()).getOutput().getText();

//        var chatResponse = chatClient
//                .prompt(query)
//                .call()
//                .entity(PersonEntity.class);

        List<PersonEntity> chatResponse = chatClient
                .prompt(query)
                .call()
                .entity(
                        new ParameterizedTypeReference<List<PersonEntity>>() {
                        }
                );

        return chatResponse;

    }

}
