package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class LineCommentLexerStateTest {

	private final LexerState lexerState = new LineCommentLexerState();

	@Test
	public void test() throws IOException {
		final String input = "--test\ntest";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(lexerState.test(in.read(), in));
			final Token token = lexerState.enter(in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}

	@Test
	public void testWindows() throws IOException {
		final String input = "--test\r\ntest";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(lexerState.test(in.read(), in));
			final Token token = lexerState.enter(in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "--test";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertTrue(lexerState.test(in.read(), in));
			final Token token = lexerState.enter(in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}


}
