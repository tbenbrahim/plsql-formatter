package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class NumericLexerState extends AbstractLexerState {

	private enum State {
		Number, Decimal, ExponentSymbol, Exponent
	}

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (Character.isDigit(currentCharacter)) {
			State state = State.Number;
			addCharacter(currentCharacter);
			int c;
			loop: while ((c = inputStream.peek()) != -1) {
				switch (state) {
				case Number:
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else if (c == '.') {
						addCharacter(inputStream.read());
						state = State.Decimal;
					} else if (c == 'E' || c == 'e') {
						addCharacter(inputStream.read());
						state = State.ExponentSymbol;
					} else {
						break loop;
					}
					break;
				case Decimal:
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else if (c == 'E' || c == 'e') {
						addCharacter(inputStream.read());
						state = State.ExponentSymbol;
					} else {
						break loop;
					}
					break;
				case ExponentSymbol:
					if (Character.isDigit(c) || c == '-') {
						addCharacter(inputStream.read());
						state = State.Exponent;
					} else {
						break loop;
					}
					break;
				case Exponent:
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else {
						break loop;
					}
					break;
				}
			}
			return TokenType.NUMBER;
		}
		return null;
	}

}
