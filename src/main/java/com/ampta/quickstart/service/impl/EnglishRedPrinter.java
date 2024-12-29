package com.ampta.quickstart.service.impl;

import org.springframework.stereotype.Component;

import com.ampta.quickstart.service.RedPrinter;

@Component
public class EnglishRedPrinter implements RedPrinter {

	@Override
	public String print() {
		return "Red";
	}

}
