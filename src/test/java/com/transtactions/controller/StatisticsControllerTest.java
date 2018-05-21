package com.transtactions.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.transactions.TransactionsRestfulApiApplication;
import com.transactions.controller.StatisticsController;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
@ContextConfiguration(classes={TransactionsRestfulApiApplication.class})
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getStatistics() throws Exception {
		mockMvc.perform(get("/statistics")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
}
