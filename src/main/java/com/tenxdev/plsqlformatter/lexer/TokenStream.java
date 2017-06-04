package com.tenxdev.plsqlformatter.lexer;

import java.io.IOException;

public interface TokenStream {

	public Token next() throws IOException;

}
