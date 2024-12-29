package com.ampta.quickstart.service.impl;

import com.ampta.quickstart.service.BluePrinter;
import com.ampta.quickstart.service.RedPrinter;

public class EnglishBluePrinter implements BluePrinter {

	@Override
	public String print() {
		return "Blue";
	}

}
