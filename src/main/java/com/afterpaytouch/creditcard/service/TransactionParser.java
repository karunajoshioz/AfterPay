package com.afterpaytouch.creditcard.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.afterpaytouch.creditcard.model.Transaction;

/**
 * This class parses raw credit card transaction data into a transaction object
 * 
 * @author Hans
 */
@Component
public class TransactionParser {

	/**
	 * Parses a string of comma separated data into a Transaction
	 * 
	 * Each line contains three comma separated fields: hashed credit card number,
	 * timestamp, price
	 * 
	 * @param input
	 *            String with comma separated input data
	 * @return Transaction containing parsed input data
	 */
	public Transaction parse(String input) {

		if (input == null || input.isEmpty())
			throw new IllegalArgumentException("Input cannot be null or empty");

		// actual parsing
		String[] sections = input.split(",");

		String creditCardNumberHashed = sections[0].trim();
		LocalDateTime timeStamp = LocalDateTime.parse(sections[1].trim(), DateTimeFormatter.ISO_DATE_TIME);
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(sections[2].trim()));

		// basic sanity check...
		validate(creditCardNumberHashed, timeStamp, price);

		return new Transaction(creditCardNumberHashed, timeStamp, price);
	}

	/**
	 * Validates that input arguments are not null/empty. Throws
	 * IllegalArgumentException if any are null.
	 * 
	 * @param creditCardNumberHashed
	 *            String
	 * @param timeStamp
	 *            LocalDateTime
	 * @param price
	 *            BigDecimal
	 */
	private void validate(String creditCardNumberHashed, LocalDateTime timeStamp, BigDecimal price) {

		if (creditCardNumberHashed == null || creditCardNumberHashed.isEmpty())
			throw new IllegalArgumentException("creditCardNumberHashed parsed as null or empty");
		if (timeStamp == null)
			throw new IllegalArgumentException("timeStamp parsed as null");
		if (price == null)
			throw new IllegalArgumentException("price parsed as null");
	}
}