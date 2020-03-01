package com.afterpaytouch.creditcard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afterpaytouch.creditcard.model.Transaction;

@Service
public class FraudDetectorImpl implements FraudDetector{
	
	@Autowired
	TransactionValidator txnValidator;
	
	@Autowired
	RawTxnDataService rawDataService;
	


	public List<String> detectFraud(LocalDate today, BigDecimal amountThreshold)
	{
		// get transactions
		List<Transaction> transactions = rawDataService.getParsedTransactions(rawDataService.getRawData());
		// check
		List<String> suspectHashedCardNumbers = txnValidator.getCardsWithDailyTotalsOverThreshold(transactions,
				today, amountThreshold);

		return suspectHashedCardNumbers;
	}
}
