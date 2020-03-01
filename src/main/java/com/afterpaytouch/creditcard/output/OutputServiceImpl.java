package com.afterpaytouch.creditcard.output;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OutputServiceImpl implements OutputService {

    @Override
    public void printResults(List<String> cardNumbers, LocalDate day, BigDecimal amountThreshold) {
    	System.out.println(getResultsHeading(cardNumbers, day, amountThreshold));
		cardNumbers.forEach(System.out::println);
		System.out.println();
    }
    
    public void displayInputData(List<String> input) {
		System.out.println("Input data: ");
		input.forEach(System.out::println);
		System.out.println();
	}
    
    /**
     * 
     * @param cardNumbers
     * @param day
     * @param amountThreshold
     * @return
     */
    private String getResultsHeading(List<String> cardNumbers, LocalDate day, BigDecimal amountThreshold) {
		if (cardNumbers == null || day == null || amountThreshold == null)
			throw new IllegalArgumentException("None of the arguments may be null");

		StringBuilder sb = new StringBuilder();

		if (cardNumbers.isEmpty())
			sb.append("No suspect");
		else
			sb.append("Suspect");

		sb.append(" credit card numbers found for ");
		sb.append(day.format(DateTimeFormatter.ISO_LOCAL_DATE));
		sb.append(", with total daily transaction amount over threshold of ");
		sb.append(String.format("%5.2f", amountThreshold.doubleValue()));

		return sb.toString();
	}
}
