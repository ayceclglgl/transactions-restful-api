package com.transactions.service;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;

import com.transactions.model.Statistic;
import com.transactions.model.Transaction;
import com.transactions.util.TranscationsUtil;

public class StatisticsServiceImpl implements StatisticsService {
	
	ConcurrentHashMap<Long, Statistic> statisticsConcurentMap = new ConcurrentHashMap<>();

	public synchronized void createStatistic(Transaction transaction) {
		Statistic statistic = null;
		if (statisticsConcurentMap.containsKey(transaction.getTimestamp())) {
			statistic = statisticsConcurentMap.get(transaction.getTimestamp());
			statistic.setSum(statistic.getSum() + transaction.getAmount());
			if (transaction.getAmount() > statistic.getMax()) {
				statistic.setMax(transaction.getAmount());
			}
			if (transaction.getAmount() < statistic.getMin()) {
				statistic.setMin(transaction.getAmount());
			}
			statistic.setCount(statistic.getCount() + 1);
		} else {
			statistic = new Statistic(transaction.getAmount(), transaction.getAmount(), transaction.getAmount(),
					transaction.getAmount(), 1);
			statisticsConcurentMap.put(transaction.getTimestamp(), statistic);
		}

	}

	@Scheduled(fixedDelayString = "${transactionsDao.removeExpiredStatisticsInMs}")
	private void removeExpiredStatistics() {
		long oneMinuteBefore = System.currentTimeMillis() - TranscationsUtil.MINUTE;
		statisticsConcurentMap.forEach((timestamp, statistic) -> {
			if (timestamp < oneMinuteBefore) {
				statisticsConcurentMap.remove(timestamp);
			}
		});
	}

	public Statistic getStatistics() {
		double sum = 0;
		double avg = 0;
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		long count = 0;

		Statistic statistic = null;
		for (Entry<Long, Statistic> entry : statisticsConcurentMap.entrySet()) {
			statistic = entry.getValue();
			if (statistic.getMax() > max) {
				max = statistic.getMax();
			}
			if (statistic.getMin() < min) {
				min = statistic.getMin();
			}
			count += statistic.getCount();
			sum += statistic.getSum() + sum;
			avg = sum / count;
		}
		return new Statistic(sum, avg, max, min, count);
	}
}
