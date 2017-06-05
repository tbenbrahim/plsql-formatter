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
package com.tenxdev.plsqlformatter.lexer;

public class Token {

	public enum TokenType {
		UNEXPECTED_EOF, BLOCK_COMMENT, LINE_COMMENT, STRING, WHITESPACE, NEWLINE, PUNCTUATION, IDENTIFIER, OPERATOR, BLOCK_LABEL, UNEXPECTED_CHARACTER, ALIAS, NUMBER, KEYWORD;

		public boolean isCasePreserved() {
			switch (this) {
			case KEYWORD:
			case IDENTIFIER:
			case BLOCK_LABEL:
				return false;
			default:
				return true;
			}
		}
	}

	private final TokenType tokenType;

	private final String text;

	public Token(TokenType tokenType, String text) {
		this.tokenType = tokenType;
		this.text = text == null ? "" : text;
	}

	public String getText() {
		return text;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", tokenType.name(), text);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Token) {
			Token other = (Token) obj;
			return (tokenType == other.tokenType) && (tokenType.isCasePreserved() ? text.compareTo(other.text) == 0
					: text.compareToIgnoreCase(other.text) == 0);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (tokenType.name() + (tokenType.isCasePreserved() ? text : text.toLowerCase())).hashCode();
	}
}
