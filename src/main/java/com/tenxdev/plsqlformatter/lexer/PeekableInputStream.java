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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * An input stream wrapper that can peek one character ahead (all that is needed
 * for PL/SQL)
 *
 */
public class PeekableInputStream extends InputStream {

	private static final int UNAVAILABLE = -2;
	private int peekCharacter = UNAVAILABLE;
	private final InputStream inputStream;

	public PeekableInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public PeekableInputStream(String input) {
		this(input, StandardCharsets.UTF_8);
	}

	public PeekableInputStream(String input, Charset charset) {
		this(new ByteArrayInputStream(input.getBytes(charset)));
	}

	/**
	 * Peeks the next character to see if it matches, if it does, consumes it
	 * and returns true, if it does not return false
	 *
	 * @param c
	 *            the character to match
	 * @return true if the next character matches, false otherwise
	 * @throws IOException
	 */
	public boolean nextIs(int c) throws IOException {
		if (peek() == c) {
			peekCharacter = UNAVAILABLE;
			return true;
		}
		return false;
	}

	/**
	 * Peeks at the next character. May be called multiple times, but always
	 * returns the character after the last character read with read()
	 *
	 * @return the next character or -1 on end of stream
	 * @throws IOException
	 */
	public int peek() throws IOException {
		if (peekCharacter == UNAVAILABLE) {
			peekCharacter = inputStream.read();
		}
		return peekCharacter;
	}

	@Override
	public int read() throws IOException {
		int result;
		if (peekCharacter == UNAVAILABLE) {
			result = inputStream.read();
		} else {
			result = peekCharacter;
			peekCharacter = UNAVAILABLE;
		}
		return result;
	}

}
