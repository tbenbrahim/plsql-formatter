package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class UnexpectedInputLexerStateTest {

	private final UnexpectedInputLexerState unexpectedInputLexerState = new UnexpectedInputLexerState();

	@Test
	public void test() throws IOException {
		final String input = "abc";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = unexpectedInputLexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_CHARACTER, token.getTokenType());
			assertEquals("a", token.getText());
			assertEquals('b', in.peek());
		}
	}

}
