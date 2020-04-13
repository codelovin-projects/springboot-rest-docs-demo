package com.codelovin.springboot.restdocstestsdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

@WebMvcTest(PersonRestController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith({ RestDocumentationExtension.class })
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonService personService;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		FieldDescriptor[] book = new FieldDescriptor[] {
				fieldWithPath("message").description("The greeting message for the user") };

		this.mockMvc.perform(get("/api/persons/hello")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")))
				.andDo(document("home", responseFields(book)));
	}

	@Test
	public void saveShouldReturnPerson() throws Exception {
		Person p = new Person(null, "David", "Smith", "david@example.com");
		mockMvc.perform(
				post("/api/persons/").contentType("application/json").content(objectMapper.writeValueAsString(p)))
				.andExpect(status().isOk()).andDo(document("save-person"));

		// Here service method accept object of Person class.
		ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
		// verify call checks that saveEmployee() method has been called exactly once.
		verify(personService, times(1)).savePerson(personCaptor.capture());
		assertThat(personCaptor.getValue().getFirstName()).isEqualTo("David");
	}
}
