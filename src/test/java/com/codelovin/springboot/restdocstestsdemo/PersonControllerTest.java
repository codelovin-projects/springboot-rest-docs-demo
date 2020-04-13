package com.codelovin.springboot.restdocstestsdemo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonRestController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
	@MockBean
    private PersonService personService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/persons/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, World")))
            .andDo(document("home"));
    }
}
