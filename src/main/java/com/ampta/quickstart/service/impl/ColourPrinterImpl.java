package com.ampta.quickstart.service.impl;

import com.ampta.quickstart.service.BluePrinter;
import com.ampta.quickstart.service.ColourPrinter;
import com.ampta.quickstart.service.GreenPrinter;
import com.ampta.quickstart.service.RedPrinter;

public class ColourPrinterImpl implements ColourPrinter{

	private RedPrinter redPrinter;
	private BluePrinter bluePrinter;
	private GreenPrinter greenPrinter;
	
	public ColourPrinterImpl() {
		this.redPrinter = new EnglishRedPrinter();
		this.bluePrinter = new EnglishBluePrinter();
		this.greenPrinter = new EnglishGreenPrinter();
	}

	@Override
	public String print() {
		return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
	}

}
