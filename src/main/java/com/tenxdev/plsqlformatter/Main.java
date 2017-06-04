package com.tenxdev.plsqlformatter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PlSqlLexer;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.TokenStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		for (int i = 1; i <= 7; ++i) {
			try (FileInputStream input = new FileInputStream(String.format("c:\\scripts\\samples\\%d.sql", i))) {
				final TokenStream tokenStream = new PlSqlLexer().lex(input);
				Token token;
				while ((token = tokenStream.next()) != null) {
					System.out.print(token.toString() + " ");
				}
			}
		}
	}
}
