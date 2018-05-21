package com.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.model.Transaction;
import com.transactions.service.TransactionService;

@RestController
public class TransactionsController {
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value="/transactions", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity transaction(@RequestBody Transaction transaction) {
		return transactionService.createTransaction(transaction) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.NO_CONTENT); 
	}

}
