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
package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class NewLineLexerStateTest {

	private final NewLineLexerState newLineLexerState = new NewLineLexerState();

	@Test
	public void testCR() throws IOException {
		final String input = "\n123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = newLineLexerState.accept(in.read(), in);
			assertEquals(TokenType.NEWLINE, token.getTokenType());
			assertEquals("\n", token.getText());
			assertEquals('1', in.peek());
		}
	}

	@Test
	public void testCRLF() throws IOException {
		final String input = "\r\n123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = newLineLexerState.accept(in.read(), in);
			assertEquals(TokenType.NEWLINE, token.getTokenType());
			assertEquals("\n", token.getText());
			assertEquals('1', in.peek());
		}
	}

	@Test
	public void testNotNewLne() throws IOException {
		final String input = "<test>";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(newLineLexerState.accept(in.read(), in));
		}
	}

}
