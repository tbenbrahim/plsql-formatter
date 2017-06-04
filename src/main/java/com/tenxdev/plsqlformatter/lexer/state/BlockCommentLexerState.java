package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

/**
 * consumes input until closing \/*
 */
public class BlockCommentLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		int c;
		while ((c = inputStream.read()) != -1) {
			if (c == '*' && inputStream.nextIs('/')) {
				return TokenType.BLOCK_COMMENT;
			}
			addCharacter(c);
		}
		return TokenType.UNEXPECTED_EOF;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return currentCharacter == '/' && inputStream.nextIs('*');
	}

}
