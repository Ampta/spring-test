package com.ampta.quickstart.service.impl;

import com.ampta.quickstart.service.GreenPrinter;
import com.ampta.quickstart.service.RedPrinter;

public class EnglishGreenPrinter implements GreenPrinter {

	@Override
	public String print() {
		return "Green";
	}

}
