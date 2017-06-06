package com.tenxdev.plsqlformatter.formatter;

import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public interface Tokens {

	Token NEWLINE = new Token(TokenType.NEWLINE, "\n");
}
