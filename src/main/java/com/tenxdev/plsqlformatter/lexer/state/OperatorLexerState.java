/*
   Copyright 2017 Tony BenBrahim

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class OperatorLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		switch (currentCharacter) {
		case '+':
		case '*':
			addCharacter(currentCharacter);
			return TokenType.OPERATOR;
		case '=':
			if (inputStream.peek()!='>'){
				addCharacter(currentCharacter);
				return TokenType.OPERATOR;
			}
			return null;
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
