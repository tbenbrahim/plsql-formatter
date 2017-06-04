package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class OperatorLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		switch (currentCharacter) {
		case '=':
		case '+':
		case '*':
			addCharacter(currentCharacter);
			return TokenType.OPERATOR;
		case '/':
			if (inputStream.peek() != '*') {
				addCharacter(currentCharacter);
				return TokenType.OPERATOR;
			}
			return null;
		case '-':
			if (inputStream.peek() != '-') {
				addCharacter(currentCharacter);
				return TokenType.OPERATOR;
			}
			return null;
		case '>':
			addString(inputStream.nextIs('=') ? Character.toString((char) currentCharacter) + "="
					: Character.toString((char) currentCharacter));
			return TokenType.OPERATOR;
		case '<':
			if (inputStream.peek() == '<') {
				return null;
			}
			if (inputStream.nextIs('=')) {
				addString("<=");
			} else if (inputStream.nextIs('>')) {
				addString("<>");
			} else {
				addString("<");
			}
			return TokenType.OPERATOR;
		case '|':
			if (inputStream.nextIs('|')) {
				addString("||");
				return TokenType.OPERATOR;
			}
			return null;
		case '!':
		case '^':
			if (inputStream.nextIs('=')) {
				addString(Character.toString((char) currentCharacter) + "=");
				return TokenType.OPERATOR;
			}
			return null;
		case ':':
			if (inputStream.nextIs('=')) {
				addString(":=");
				return TokenType.OPERATOR;
			}
			return null;
		default:
			return null;
		}
	}

}
