package com.transactions.service;

import com.transactions.model.Statistic;
import com.transactions.model.Transaction;

public interface StatisticsService {
	public void createStatistic(Transaction transaction);
	public Statistic getStatistics();
}
