package com.ampta.quickstart.service.impl;

import org.springframework.stereotype.Component;

import com.ampta.quickstart.service.BluePrinter;
import com.ampta.quickstart.service.ColourPrinter;
import com.ampta.quickstart.service.GreenPrinter;
import com.ampta.quickstart.service.RedPrinter;

@Component
public class ColourPrinterImpl implements ColourPrinter{

	private RedPrinter redPrinter;
	private BluePrinter bluePrinter;
	private GreenPrinter greenPrinter;
	
	public ColourPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
		this.redPrinter = redPrinter;
		this.bluePrinter = bluePrinter;
		this.greenPrinter = greenPrinter;
	}

	@Override
	public String print() {
		return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
	}

}
