package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

/**
 * Consumes input until closing single quote, deals with quote escapes ('')
 */
public class AliasLexerState extends AbstractLexerState {

	private static final char DOUBLE_QUOTE = '"';

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (currentCharacter == DOUBLE_QUOTE){
			int c;
			while ((c = inputStream.read()) != -1) {
				if (c == DOUBLE_QUOTE && !inputStream.nextIs(DOUBLE_QUOTE)) {
					return TokenType.ALIAS;
				}
				addCharacter(c);
			}
			return TokenType.UNEXPECTED_EOF;
		}
		return null;
	}

}
