package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class NewLineLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (currentCharacter == '\n' || currentCharacter == '\r' && inputStream.nextIs('\n')){
			addCharacter('\n');
			return TokenType.NEWLINE;
		}
		return null;
	}

}
