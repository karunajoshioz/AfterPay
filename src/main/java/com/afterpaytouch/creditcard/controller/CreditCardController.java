package com.afterpaytouch.creditcard.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.afterpaytouch.creditcard.exception.GenericCreditCardException;
import com.afterpaytouch.creditcard.output.OutputService;
import com.afterpaytouch.creditcard.service.FraudDetector;
import com.afterpaytouch.creditcard.service.RawTxnDataService;


@Component
public class CreditCardController {
	
	private static final BigDecimal amountThreshold1 = BigDecimal.valueOf(850.0);
	private static final BigDecimal amountThreshold2 = BigDecimal.valueOf(250.0);
	
	@Autowired
	OutputService outputservice;
	
	@Autowired
	RawTxnDataService rawDataService;
	
	@Autowired
	FraudDetector fraudDetector;
	
	/**
	 * 
	 * @throws GenericCreditCardException
	 */
	 public void process() throws  GenericCreditCardException{
		 
		 //Display Input data
		 outputservice.displayInputData(rawDataService.getRawData());
		 
		 LocalDate today = LocalDate.parse("2019-01-25", DateTimeFormatter.ISO_DATE);
		 
		// test 1
		 List<String> suspectedCardNos1 = fraudDetector.detectFraud(today, amountThreshold1);
		 outputservice.printResults(suspectedCardNos1, today, amountThreshold1);
		// test 2
		 List<String> suspectedCardNos2 = fraudDetector.detectFraud(today, amountThreshold2);
		 outputservice.printResults(suspectedCardNos2, today, amountThreshold2);

		// Yesterday:
		// test 3
		 List<String> suspectedCardNos3 = fraudDetector.detectFraud(today.minusDays(1), amountThreshold1);
		 outputservice.printResults(suspectedCardNos3, today, amountThreshold1);
		 
		// test 4
		 List<String> suspectedCardNos4 = fraudDetector.detectFraud(today.minusDays(1), amountThreshold2);
		 outputservice.printResults(suspectedCardNos4, today, amountThreshold2);
		
	            
	 }

}
