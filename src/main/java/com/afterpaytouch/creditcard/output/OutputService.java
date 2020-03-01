package com.afterpaytouch.creditcard.output;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OutputService {
	
	public void printResults(List<String> cardNumbers, LocalDate day, BigDecimal amountThreshold);
	public void displayInputData(List<String> input) ;

}
