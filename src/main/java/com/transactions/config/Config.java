package com.transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.transactions.service.StatisticsService;
import com.transactions.service.StatisticsServiceImpl;
import com.transactions.service.TransactionService;
import com.transactions.service.TransactionServiceImpl;

@Configuration
public class Config {
	@Bean
	TransactionService transactionService() {
		return new TransactionServiceImpl();
	}
	
	@Bean
	StatisticsService statisticsService() {
		return new StatisticsServiceImpl();
	}
}
