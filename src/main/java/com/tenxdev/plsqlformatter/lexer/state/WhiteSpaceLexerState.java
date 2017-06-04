package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class WhiteSpaceLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		while (isSpaceOrTab(inputStream.peek())) {
			inputStream.read();
		}
		return TokenType.WHITESPACE;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return isSpaceOrTab(currentCharacter);
	}

	private boolean isSpaceOrTab(int currentCharacter) {
		return currentCharacter == ' ' || currentCharacter == '\t';
	}

}
