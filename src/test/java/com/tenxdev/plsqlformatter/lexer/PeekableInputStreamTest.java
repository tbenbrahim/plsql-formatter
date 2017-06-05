package com.tenxdev.plsqlformatter.lexer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import static org.junit.Assert.*;

public class PeekableInputStreamTest {

	@Test
	public void testSimpleRead() throws UnsupportedEncodingException, IOException {
		String inputString = "Hello World";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)))) {
			StringBuilder output = new StringBuilder();
			int c;
			while ((c = in.read()) != -1) {
				output.append((char) c);
			}
			assertEquals(inputString, output.toString());
		}
	}

	@Test
	public void testPeekRead() throws UnsupportedEncodingException, IOException {
		String inputString = "Hello World";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)))) {
			StringBuilder output = new StringBuilder();
			int c;
			while ((c = in.peek()) != -1) {
				output.append((char) c);
				in.read();
			}
			assertEquals(inputString, output.toString());
		}
	}

}
