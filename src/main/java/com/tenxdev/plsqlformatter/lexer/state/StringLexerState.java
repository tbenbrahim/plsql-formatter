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

/**
 * Consumes input until closing single quote, deals with quote escapes ('')
 */
public class StringLexerState extends AbstractLexerState {

	private static final char SINGLE_QUOTE = '\'';

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (currentCharacter == SINGLE_QUOTE){
			int c;
			while ((c = inputStream.read()) != -1) {
				if (c == SINGLE_QUOTE && !inputStream.nextIs(SINGLE_QUOTE)) {
					return TokenType.STRING;
				}
				addCharacter(c);
			}
			return TokenType.UNEXPECTED_EOF;
		}
		return null;
	}

}
