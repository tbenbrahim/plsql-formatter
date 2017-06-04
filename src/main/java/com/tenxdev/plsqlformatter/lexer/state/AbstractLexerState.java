package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;
import java.util.Set;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public abstract class AbstractLexerState implements LexerState {

	private String text = "";
	private LexerState nextLexerState;

	protected void addCharacter(int c) {
		text += Character.toString((char) c);
	}

	protected void addString(String input) {
		text += input;
	}

	protected abstract TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException;

	protected boolean textIsInSet(Set<String> set) {
		return set.contains(text.toUpperCase());
	}

	@Override
	public Token accept(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		text = "";
		TokenType tokenType = process(currentCharacter, inputStream);
		return tokenType == null ? next(currentCharacter, inputStream) : new Token(tokenType, text);
	}

	private Token next(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return nextLexerState == null ? null : nextLexerState.accept(currentCharacter, inputStream);
	}

	@Override
	public final LexerState setNextLexerState(LexerState lexerState) {
		this.nextLexerState = lexerState;
		return nextLexerState;
	}

}
