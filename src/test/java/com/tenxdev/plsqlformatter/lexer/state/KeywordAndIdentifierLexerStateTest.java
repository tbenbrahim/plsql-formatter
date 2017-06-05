package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class KeywordAndIdentifierLexerStateTest {

	private final KeywordAndIdentifierLexerState lexerState = new KeywordAndIdentifierLexerState();

	@Test
	public void testIdentifier() throws IOException {
		final String input = "abc_123$#/abc";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.IDENTIFIER, token.getTokenType());
			assertEquals("abc_123$#", token.getText());
			assertEquals('/', in.peek());
		}
	}

	@Test
	public void testKeyword() throws IOException {
		final String input = "end if";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.KEYWORD, token.getTokenType());
			assertEquals("end", token.getText());
			assertEquals(' ', in.peek());
		}
	}

	@Test
	public void testNotIdentifier() throws IOException {
		final String input = "'test'";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(lexerState.accept(in.read(), in));
		}
	}

}
