package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.*;

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
			final Token token = lexerState.accept(in.read(),in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
			assertEquals('\n', in.peek());
		}
	}

	@Test
	public void testWindows() throws IOException {
		final String input = "--test\r\ntest";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
			assertEquals('\r', in.peek());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "--test";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.LINE_COMMENT, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}
	
	@Test
	public void testNotLineComment() throws IOException{
		final String input = "//test";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = lexerState.accept(in.read(), in);
			assertNull(token);
		}
	}


}
