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
