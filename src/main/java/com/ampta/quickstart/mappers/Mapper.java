package com.ampta.quickstart.mappers;

public interface Mapper<A, B> {
	
	B mapTo(A a);
	
	A mapFrom(B b);
}
