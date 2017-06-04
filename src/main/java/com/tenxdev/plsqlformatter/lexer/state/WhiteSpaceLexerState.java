package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class WhiteSpaceLexerState extends AbstractLexerState {

	private boolean isSpaceOrTab(int currentCharacter) {
		return currentCharacter == ' ' || currentCharacter == '\t';
	}

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (isSpaceOrTab(currentCharacter)){
			addCharacter(currentCharacter);
			while (isSpaceOrTab(inputStream.peek())) {
				addCharacter(inputStream.read());
			}
			return TokenType.WHITESPACE;
		}
		return null;
	}

}
