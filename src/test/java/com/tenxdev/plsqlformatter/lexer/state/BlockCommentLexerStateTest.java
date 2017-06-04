package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class BlockCommentLexerStateTest {

	private final BlockCommentLexerState blockCommentLexerState = new BlockCommentLexerState();

	@Test
	public void test() throws IOException {
		final String input = "/*test*1*/";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockCommentLexerState.accept(in.read(), in);
			assertEquals(TokenType.BLOCK_COMMENT, token.getTokenType());
			assertEquals("test*1", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "/*test****";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockCommentLexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testNotBlockComment() throws IOException {
		final String input = "//test****";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(blockCommentLexerState.accept(in.read(), in));
		}

	}

}
