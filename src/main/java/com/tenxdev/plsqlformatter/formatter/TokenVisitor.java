package com.tenxdev.plsqlformatter.formatter;

import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public interface TokenVisitor {

	Token beforeToken(Token token);
	Token onToken(Token token);
	Token afterToken(Token token);
	TokenType getHandledTokenType();
}
