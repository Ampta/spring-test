package com.ampta.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ampta.quickstart.service.BluePrinter;
import com.ampta.quickstart.service.ColourPrinter;
import com.ampta.quickstart.service.GreenPrinter;
import com.ampta.quickstart.service.RedPrinter;
import com.ampta.quickstart.service.impl.ColourPrinterImpl;
import com.ampta.quickstart.service.impl.spanish.SpanishBluePrinter;
import com.ampta.quickstart.service.impl.spanish.SpanishGreenPrinter;
import com.ampta.quickstart.service.impl.spanish.SpanishRedPrinter;

@Configuration // It tells spring-boot look for bean declaration
public class PrinterConfig {
	
	@Bean
	public RedPrinter redPrinter() {
		return new SpanishRedPrinter();
	}
	
	@Bean
	public BluePrinter bluePrinter() {
		return new SpanishBluePrinter();
	}
	
	@Bean
	public GreenPrinter greenPrinter() {
		return new SpanishGreenPrinter();
	}
	
	@Bean
	public ColourPrinter colourPrinter(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
		return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
	}
}
