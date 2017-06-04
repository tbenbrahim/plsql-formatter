package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

/**
 * Consumes input until closing single quote, deals with quote escapes ('')
 */
public class StringLexerState extends AbstractLexerState {

	private static final char SINGLE_QUOTE = '\'';

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		int c;
		while ((c = inputStream.read()) != -1) {
			if (c == SINGLE_QUOTE && !inputStream.nextIs(SINGLE_QUOTE)) {
				return TokenType.STRING;
			}
			addCharacter(c);
		}
		return TokenType.UNEXPECTED_EOF;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return currentCharacter == SINGLE_QUOTE;
	}

}
