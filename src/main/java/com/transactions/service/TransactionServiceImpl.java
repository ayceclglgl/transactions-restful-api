package com.transactions.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.transactions.model.Transaction;
import com.transactions.util.TranscationsUtil;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	StatisticsService statisticsService;

	public synchronized boolean createTransaction(Transaction transaction) {
		if(isValidTime(transaction.getTimestamp())){
			statisticsService.createStatistic(transaction);
			return true;
		}
		return false;
	}
	
	private boolean isValidTime(long time) {
		long now = System.currentTimeMillis();
		return time < now && now - time <= TranscationsUtil.MINUTE;
	}

}
