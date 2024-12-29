package com.ampta.quickstart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ampta.quickstart.service.ColourPrinter;
import com.ampta.quickstart.service.impl.ColourPrinterImpl;

@SpringBootApplication
public class QuickstartApplication implements CommandLineRunner {

	
	private ColourPrinter colourPrinter;
	
	public QuickstartApplication(ColourPrinter colourPrinter) {
		this.colourPrinter = colourPrinter;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(colourPrinter.print());
	}

}
