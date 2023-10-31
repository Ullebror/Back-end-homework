package com.Bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RestTest {

	@Autowired
	private MockMvc mockMvc;

	/*@WithMockUser(value = "admin")
	@Test
	public void testBookListRest() throws Exception {
		this.mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[1].id", Matchers.equalTo(2))); 

	} */

	/*
	 * Not enough information in lecture materials to figure out what to do with
	 * Rest search.
	 * 
	 * @WithMockUser(value = "admin")
	 * 
	 * @Test public void testBookSearchRest() throws Exception {
	 * this.mockMvc.perform(get("/book/1").accept(MediaType.APPLICATION_JSON)).
	 * andExpect(status().isOk()) .andExpect(jsonPath("$[0].title",
	 * Matchers.equalTo("A Farewell to Arms")));
	 * 
	 * }
	 */

}
