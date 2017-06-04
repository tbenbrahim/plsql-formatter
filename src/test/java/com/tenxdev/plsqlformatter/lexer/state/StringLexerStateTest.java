package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class StringLexerStateTest {

	private final StringLexerState stringLexerState = new StringLexerState();

	@Test
	public void test() throws IOException {
		final String input = "'test'";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(stringLexerState.test(in.read(), in));
			final Token token = stringLexerState.enter(in);
			assertEquals(TokenType.STRING, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "'''test'' ''one'' and test ''two''";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(stringLexerState.test(in.read(), in));
			final Token token = stringLexerState.enter(in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testEscape() throws IOException {
		final String input = "'''test'' ''one'' and test ''two'''";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(stringLexerState.test(in.read(), in));
			final Token token = stringLexerState.enter(in);
			assertEquals(TokenType.STRING, token.getTokenType());
			assertEquals("'test' 'one' and test 'two'", token.getText());
		}
	}

}
