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
			while ((c = inputStream.peek()) != -1) {
				if (state == State.Number) {
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else if (c == '.') {
						addCharacter(inputStream.read());
						state = State.Decimal;
					} else if (c == 'E' || c == 'e') {
						addCharacter(inputStream.read());
						state = State.ExponentSymbol;
					} else {
						break;
					}
				} else if (state == State.Decimal) {
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else if (c == 'E' || c == 'e') {
						addCharacter(inputStream.read());
						state = State.ExponentSymbol;
					} else {
						break;
					}
				} else if (state == State.ExponentSymbol) {
					if (Character.isDigit(c) || c == '-') {
						addCharacter(inputStream.read());
						state = State.Exponent;
					} else {
						break;
					}
				} else if (state == State.Exponent) {
					if (Character.isDigit(c)) {
						addCharacter(inputStream.read());
					} else {
						break;
					}
				} else{
					break;
				}
			}
			return TokenType.NUMBER;
		}
		return null;
	}

}
