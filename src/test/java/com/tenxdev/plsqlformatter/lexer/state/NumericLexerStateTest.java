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

public class NumericLexerStateTest {

	private final NumericLexerState numericLexerState = new NumericLexerState();

	@Test
	public void testInteger() throws IOException {
		final String input = "123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123", token.getText());
		}
	}

	@Test
	public void testDecimal() throws IOException {
		final String input = "123.1ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123.1", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testScientific() throws IOException {
		final String input = "123.1E12ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123.1E12", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testScientificSmalle() throws IOException {
		final String input = "123.1e12ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123.1e12", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testScientificNoDecimal() throws IOException {
		final String input = "123E12ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123E12", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testScientificSmalleNoDecimal() throws IOException {
		final String input = "123e12ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123e12", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testSmallScientific() throws IOException {
		final String input = "123.1E-12ABC";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123.1E-12", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testNotScientific() throws IOException {
		final String input = "123.1EACH";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = numericLexerState.accept(in.read(), in);
			assertEquals(TokenType.NUMBER, token.getTokenType());
			assertEquals("123.1E", token.getText());
			assertEquals('A', in.peek());
		}
	}

	@Test
	public void testNotNumber() throws IOException {
		final String input = "<test>";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(numericLexerState.accept(in.read(), in));
		}
	}

}
