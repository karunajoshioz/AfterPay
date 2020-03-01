package com.afterpaytouch.creditcard.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data class for a credit card transaction
 * 
 * @author Hans
 */
public class Transaction {
	private String creditCardNumberHashed;
	private LocalDateTime timestamp;
	private BigDecimal price;

	public Transaction(String creditCardNumberHashed, LocalDateTime timestamp, BigDecimal price) {
		super();
		this.creditCardNumberHashed = creditCardNumberHashed;
		this.timestamp = timestamp;
		this.price = price;
	}

	public String getCreditCardNumberHashed() {
		return creditCardNumberHashed;
	}

	public Transaction setCreditCardNumberHashed(String creditCardNumberHashed) {
		this.creditCardNumberHashed = creditCardNumberHashed;
		return this;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Transaction setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public double getPriceAsDouble() {
		return price.doubleValue();
	}

	public Transaction setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	@Override
	public String toString() {
		return "Transaction [creditCardNumberHashed=" + creditCardNumberHashed + ", timestamp=" + timestamp + ", price="
				+ price + "]";
	}
}
