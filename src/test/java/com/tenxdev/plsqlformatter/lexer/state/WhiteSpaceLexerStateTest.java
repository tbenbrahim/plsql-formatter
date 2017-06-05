package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class WhiteSpaceLexerStateTest {

	private final WhiteSpaceLexerState whiteSpaceLexerState = new WhiteSpaceLexerState();

	@Test
	public void test() throws IOException {
		final String input = "   \t  123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = whiteSpaceLexerState.accept(in.read(), in);
			assertEquals(TokenType.WHITESPACE, token.getTokenType());
			assertEquals("   \t  ", token.getText());
			assertEquals('1', in.peek());
		}
	}

	@Test
	public void testNotSWiteSpace() throws IOException {
		final String input = "<test>";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(whiteSpaceLexerState.accept(in.read(), in));
		}
	}

}
