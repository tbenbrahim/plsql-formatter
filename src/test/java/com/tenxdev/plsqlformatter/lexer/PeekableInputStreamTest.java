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
		String commentText = "***comment * text***";
		String inputString = "/*" + commentText + "*/ test";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)))) {
			StringBuilder output = new StringBuilder();
			int c;
			boolean inComment = false;
			while ((c = in.read()) != -1) {
				if (!inComment && c == '/') {
					if (in.nextIs('*')) {
						inComment = true;
						continue;
					}
				}
				if (inComment) {
					if (c == '*' && in.nextIs('/')) {
						inComment = false;
						continue;
					}
					output.append((char) c);
				}
			}
			assertEquals(commentText, output.toString());
		}
	}

}
