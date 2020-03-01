package com.afterpaytouch.creditcard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.afterpaytouch.creditcard.model.Transaction;

@Service
public class RawTxnDataService {
	
	@Autowired
	TransactionParser txnparser;
	
	//TODO
	//Read this from file - rawdata.txt
	public List<String> getRawData() {
		return Arrays.asList("1117ce2f43e35fa57d1bbf8b1e2, 2019-01-25T13:15:54, 100.00",
				"1117ce2f43e35fa57d1bbf8b1e2, 2019-01-25T14:15:54, 100.00",
				"1117ce2f43e35fa57d1bbf8b1e3, 2019-01-25T15:15:54, 400.00",
				"1117ce2f43e35fa57d1bbf8b1e3, 2019-01-25T16:15:54, 400.00",
				"1117ce2f43e35fa57d1bbf8b1e3, 2019-01-25T17:15:54, 500.00",
				"1117ce2f43e35fa57d1bbf8b1e4, 2019-01-25T18:15:54, 1300.00",
				"1117ce2f43e35fa57d1bbf8b1e5, 2019-01-25T18:15:54, 1250.00",
				"8887ce2f43e35fa57d1bbf8b1e7, 2019-02-25T13:15:54, 510.00",
				"8887ce2f43e35fa57d1bbf8b1e8, 2019-02-25T14:15:54, 220.00",
				"8887ce2f43e35fa57d1bbf8b1e9, 2019-02-25T15:15:54, 330.00",
				"8887ce2f43e35fa57d1bbf8b1e7, 2019-02-25T16:15:54, 940.00");
	}
	
	/**
	 * Get a list of transactions based on a list of raw data
	 * 
	 * @param rawDataList
	 *            List<String> with raw comma separated data
	 * @return List<Transaction> containing parsed input data
	 */
	public List<Transaction> getParsedTransactions(List<String> rawDataList) {

		if (rawDataList == null || rawDataList.isEmpty())
			return Collections.emptyList();

		List<Transaction> parsedTransactions = new ArrayList<>();
		for (String rawData : rawDataList)
			parsedTransactions.add(txnparser.parse(rawData));

		return parsedTransactions;
	}

}
