/*
   Copyright 2017 Tony BenBrahim

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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
