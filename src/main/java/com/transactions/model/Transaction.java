package com.transactions.model;

import javax.validation.constraints.NotNull;


public class Transaction {
    @NotNull
    private double amount;
    @NotNull
    private long timestamp;

    public Transaction() {}
    
	public Transaction(double amount, long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getAmount() {
		return amount;
	}
	public long getTimestamp() {
		return timestamp;
	}
	
}


