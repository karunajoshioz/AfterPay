package com.afterpaytouch.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.afterpaytouch.creditcard.controller.CreditCardController;
import com.afterpaytouch.creditcard.exception.GenericCreditCardException;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class CreditCardFraudDetectorApplication {

	
	public static void main(String[] args) {
		//SpringApplication.run(CreditCardFraudDetectorApplication.class, args);
		ConfigurableApplicationContext ctx = SpringApplication.run(CreditCardFraudDetectorApplication.class, args);
		CreditCardController controller = (CreditCardController) ctx.getBean("creditCardController");

		try{
			controller.process();
		}
		catch (GenericCreditCardException e) {
			e.printStackTrace();
		}
		ctx.close();
	}

}

