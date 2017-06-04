package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;
import java.util.Set;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public abstract class AbstractLexerState implements LexerState {

	private String text = "";

	protected void addCharacter(int c) {
		text += (char) c;
	}

	protected void addString(String input) {
		text += input;
	}

	@Override
	public Token enter(PeekableInputStream inputStream) throws IOException {
		text = "";
		return new Token(process(inputStream), text);
	}

	protected abstract TokenType process(PeekableInputStream inputStream) throws IOException;

	protected boolean textIsInSet(Set<String> set) {
		return set.contains(text.toUpperCase());
	}

}
