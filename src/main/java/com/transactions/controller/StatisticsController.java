package com.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.model.Statistic;
import com.transactions.service.StatisticsService;

@RestController
public class StatisticsController {
	@Autowired
	StatisticsService statisticsService;
	
	@RequestMapping(value= "/statistics", method = RequestMethod.GET)
	public ResponseEntity<Statistic> statistics() {
		return new ResponseEntity<Statistic>(statisticsService.getStatistics(), HttpStatus.OK);
	}
}
