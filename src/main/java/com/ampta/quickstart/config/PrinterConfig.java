package com.ampta.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ampta.quickstart.service.BluePrinter;
import com.ampta.quickstart.service.ColourPrinter;
import com.ampta.quickstart.service.GreenPrinter;
import com.ampta.quickstart.service.RedPrinter;
import com.ampta.quickstart.service.impl.ColourPrinterImpl;
import com.ampta.quickstart.service.impl.EnglishBluePrinter;
import com.ampta.quickstart.service.impl.EnglishGreenPrinter;
import com.ampta.quickstart.service.impl.EnglishRedPrinter;

@Configuration // It tells spring-boot look for bean declaration
public class PrinterConfig {
	
	@Bean
	public RedPrinter redPrinter() {
		return new EnglishRedPrinter();
	}
	
	@Bean
	public BluePrinter bluePrinter() {
		return new EnglishBluePrinter();
	}
	
	@Bean
	public GreenPrinter greenPrinter() {
		return new EnglishGreenPrinter();
	}
	
	@Bean
	public ColourPrinter colourPrinter(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
		return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
	}
}
