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
		if (lexerState == this) {
			throw new IllegalArgumentException("Next state may not be the same instance");
		}
		this.nextLexerState = lexerState;
		return nextLexerState;
	}

}
