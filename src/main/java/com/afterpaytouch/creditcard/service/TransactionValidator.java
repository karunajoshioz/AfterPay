package com.afterpaytouch.creditcard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.afterpaytouch.creditcard.model.Transaction;

@Component
public class TransactionValidator {
	
	
	/**
	 * Gets a list of credit card numbers with total daily transaction values over
	 * the supplied amount
	 * 
	 * @param transactions
	 *            List of transactions to check
	 * @param day
	 *            LocalDate with day to check against
	 * @param amountThreshold
	 *            BigDecimal with amount threshold
	 * @return List of credit card numbers matching above criteria, else an empty
	 *         list
	 */
	public List<String> getCardsWithDailyTotalsOverThreshold(List<Transaction> transactions, LocalDate daytime,
			BigDecimal amountThreshold) {

		if (transactions == null || daytime == null || amountThreshold == null)
			throw new IllegalArgumentException("None of the arguments may be null");

		// get a map of hashed credit card numbers with daily totals per card
		Map<String, Double> creditCardDailyTotals = transactions.stream()
				.filter(transaction -> isTransactionOnDay(transaction, daytime))
				.collect(Collectors.groupingBy(Transaction::getCreditCardNumberHashed,
						Collectors.summingDouble(Transaction::getPriceAsDouble)));

		// if there are no transactions for the day, return empty list
		if (creditCardDailyTotals.isEmpty())
			return Collections.emptyList();

		// get a list of hashed credit card numbers with daily transaction totals over
		// the amount threshold
		List<String> cardsWithTotalsOverDailyTreshold = new ArrayList<>();
		for (String creditCardNumber : creditCardDailyTotals.keySet())
			if (creditCardDailyTotals.get(creditCardNumber) > amountThreshold.doubleValue())
				cardsWithTotalsOverDailyTreshold.add(creditCardNumber);

		return cardsWithTotalsOverDailyTreshold;
	}

	/**
	 * Determines if the supplied transaction happened on the supplied day.
	 * 
	 * @param tx
	 *            Transaction to check
	 * @param day
	 *            LocalDate to check against
	 * @return true if transaction happened on the supplied day, else false
	 */
	protected boolean isTransactionOnDay(Transaction tx, LocalDate day) {

		if (tx.getTimestamp().toLocalDate().equals(day))
			return true;

		return false;
	}
}
