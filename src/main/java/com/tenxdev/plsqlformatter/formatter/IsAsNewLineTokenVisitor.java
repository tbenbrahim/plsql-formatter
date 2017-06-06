package com.tenxdev.plsqlformatter.formatter;

import com.tenxdev.plsqlformatter.FormattingOptions;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class IsAsNewLineTokenVisitor implements TokenVisitor {

	private FormattingOptions formattingOptions;

	public IsAsNewLineTokenVisitor(FormattingOptions formattingOptions) {
		this.formattingOptions = formattingOptions;
	}

	@Override
	public Token beforeToken(Token token) {
		if (!formattingOptions.isSameLineIs()) {
			return Tokens.NEWLINE;
		}
		return null;
	}

	@Override
	public Token onToken(Token token) {
		return token;
	}

	@Override
	public Token afterToken(Token token) {
		return Tokens.NEWLINE;
	}

	@Override
	public TokenType getHandledTokenType() {
		return TokenType.KEYWORD_AS_IS;
	}


}
