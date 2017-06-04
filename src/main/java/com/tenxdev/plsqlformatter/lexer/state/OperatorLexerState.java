package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class OperatorLexerState extends AbstractLexerState {

	private String input;

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		addString(input);
		return TokenType.OPERATOR;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		switch (currentCharacter) {
		case '=':
		case '+':
		case '*':
			input = Character.toString((char) currentCharacter);
			return true;
		case '/':
			if (inputStream.peek() != '*') {
				input = "/";
				return true;
			}
			return false;
		case '-':
			if (inputStream.peek() != '-') {
				input = "-";
				return true;
			}
			return false;
		case '>':
			input = inputStream.nextIs('=') ? Character.toString((char) currentCharacter) + "="
					: Character.toString((char) currentCharacter);
			return true;
		case '<':
			if (inputStream.peek() == '<') {
				return false;
			}
			if (inputStream.nextIs('=')) {
				input = "<=";
			} else if (inputStream.nextIs('>')) {
				input = "<>";
			} else {
				input = "<";
			}
			return true;
		case '|':
			if (inputStream.nextIs('|')) {
				input = "||";
				return true;
			}
			return false;
		case '!':
		case '^':
			if (inputStream.nextIs('=')) {
				input = Character.toString((char) currentCharacter) + "=";
				return true;
			}
			return false;
		case ':':
			if (inputStream.nextIs('=')) {
				input = ":=";
				return true;
			}
			return false;
		}

		return false;
	}

}
