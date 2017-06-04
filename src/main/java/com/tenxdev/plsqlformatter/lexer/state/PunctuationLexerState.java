package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class PunctuationLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		switch (currentCharacter) {
		case '.':
		case ';':
		case ',':
		case '%':
		case '(':
		case ')':
			addString(Character.toString((char) currentCharacter));
			return TokenType.PUNCTUATION;
		case '=':
			if (inputStream.nextIs('>')) {
				addString("=>");
				return TokenType.PUNCTUATION;
			}
			return null;
		}
		return null;
	}

}
