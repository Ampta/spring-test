package com.ampta.quickstart.service.impl;

import org.springframework.stereotype.Component;

import com.ampta.quickstart.service.GreenPrinter;

@Component
public class EnglishGreenPrinter implements GreenPrinter {

	@Override
	public String print() {
		return "Green";
	}

}
