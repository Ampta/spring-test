package com.ampta.quickstart.service.impl;

import org.springframework.stereotype.Component;

import com.ampta.quickstart.service.BluePrinter;

@Component
public class EnglishBluePrinter implements BluePrinter {

	@Override
	public String print() {
		return "Blue";
	}

}
