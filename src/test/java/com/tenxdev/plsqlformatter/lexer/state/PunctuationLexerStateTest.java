package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class PunctuationLexerStateTest {

	private final PunctuationLexerState punctuationLexerState = new PunctuationLexerState();

	private List<String> validInputs = Arrays.asList(",",";",".","%","(",")","=>");
	private List<String> invalidInputs = Arrays.asList("=","x");

	@Test
	public void testOperator() throws IOException {
		for (String expected : validInputs) {
			final String input = expected+"abc";
			try (PeekableInputStream in = new PeekableInputStream(input)) {
				final Token token = punctuationLexerState.accept(in.read(), in);
				assertEquals(TokenType.PUNCTUATION, token.getTokenType());
				assertEquals(expected, token.getText());
				assertEquals('a', in.peek());
			}
		}
	}

	@Test
	public void testNotOperator() throws IOException {
		for (String expected : invalidInputs) {
			final String input = expected+"abc";
			try (PeekableInputStream in = new PeekableInputStream(input)) {
				final Token token = punctuationLexerState.accept(in.read(), in);
				assertNull("for input "+input, token);
				assertEquals(input.charAt(1), in.peek());
			}
		}
	}

}
