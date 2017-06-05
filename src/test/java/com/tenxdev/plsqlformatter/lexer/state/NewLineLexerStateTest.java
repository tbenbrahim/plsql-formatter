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
