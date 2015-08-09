package com.goodstart.streams;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ReadStreamTest {

	private final String exampleInput = "This is some input that has a line break in the middle \nof it.";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpToEnd() {
		ReadStream str = new ReadStream(this.exampleInput);
		boolean thrown = false;
		try {
			assert(str.upToEnd() == this.exampleInput);
			assert(str.atEnd());
		} catch (IOException ex) {
			thrown = true;
		}
		assert(!thrown);
	}
	
	@Test
	public void testUpTo() {
		ReadStream str = new ReadStream(this.exampleInput);
		boolean thrown = false;
		try {
			assert(str.upTo(" ".charAt(0)) == "This");
			assert(!str.atEnd());
		} catch (IOException ex) {
			thrown = true;
		}
		assert(!thrown);
	}
	

	@Test
	public void testNext() {
		ReadStream str = new ReadStream(this.exampleInput);
		boolean thrown = false;
		try {
			assert(str.next() == "T".charAt(0));
			assert(str.next() == "h".charAt(0));
			assert(str.next() == "i".charAt(0));
			assert(str.next() == "s".charAt(0));
		} catch (IOException ex) {
			thrown = true;
		}
		assert(!thrown);
	}

	@Test
	public void testPeek() {
		ReadStream str = new ReadStream(this.exampleInput);
		boolean thrown = false;
		try {
			assert(str.next() == "T".charAt(0));
			assert(str.next() == "T".charAt(0));
		} catch (IOException ex) {
			thrown = true;
		}
		assert(!thrown);
	}
}
