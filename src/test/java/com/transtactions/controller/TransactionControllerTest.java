package com.transtactions.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.transactions.TransactionsRestfulApiApplication;
import com.transactions.controller.TransactionsController;
import com.transactions.util.TranscationsUtil;




@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
@ContextConfiguration(classes={TransactionsRestfulApiApplication.class})
public class TransactionControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	
	@Test
	public void expiredTransaction() throws Exception {
		mockMvc.perform(post("/transactions")
				.contentType("application/json")
				.content("{\"amount\": 12.3,\"timestamp\": 1478192204000}"))
				.andExpect(status().isNoContent())
				.andExpect(content().bytes(new byte[0]));
	}
	
	@Test
	public void futureTransaction() throws Exception{
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"amount\": 5.0");
		builder.append(",");
		builder.append("\"timestamp\": " + (System.currentTimeMillis() + TranscationsUtil.MINUTE));
		builder.append("}");
		
		mockMvc.perform(post("/transactions")
                .contentType("application/json")
                .content(builder.toString()))
                .andExpect(status().isNoContent())
                .andExpect(content().bytes(new byte[0]));
	}
	
	@Test
	public void validTransaction() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"amount\": 5.0");
		builder.append(",");
		builder.append("\"timestamp\": " + System.currentTimeMillis());
		builder.append("}");
		
		mockMvc.perform(post("/transactions")
				.contentType("application/json")
				.content(builder.toString()))
				.andExpect(status().isCreated())
				.andExpect(content().bytes(new byte[0]));
	}
	
}
