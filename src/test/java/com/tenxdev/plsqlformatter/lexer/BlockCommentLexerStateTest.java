package com.tenxdev.plsqlformatter.lexer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;
import com.tenxdev.plsqlformatter.lexer.state.BlockCommentLexerState;

public class BlockCommentLexerStateTest {

	@Test
	public void test() throws IOException {
		final String input = "test*1*/";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
			final Token token = new BlockCommentLexerState().enter(in);
			assertEquals(TokenType.BLOCK_COMMENT, token.getTokenType());
			assertEquals("test*1", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "test****";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
			final Token token = new BlockCommentLexerState().enter(in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testReuse() throws IOException {
		final BlockCommentLexerState lexerState = new BlockCommentLexerState();
		final String input = "foo*/bar*/";
		try (PeekableInputStream in = new PeekableInputStream(
				new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
			Token token = lexerState.enter(in);
			assertEquals(TokenType.BLOCK_COMMENT, token.getTokenType());
			assertEquals("foo", token.getText());
			token = lexerState.enter(in);
			assertEquals(TokenType.BLOCK_COMMENT, token.getTokenType());
			assertEquals("bar", token.getText());
		}
	}

}
