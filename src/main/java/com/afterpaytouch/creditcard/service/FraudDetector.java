package com.afterpaytouch.creditcard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FraudDetector{

	public List<String> detectFraud(LocalDate today, BigDecimal amountThreshold) ;
}
