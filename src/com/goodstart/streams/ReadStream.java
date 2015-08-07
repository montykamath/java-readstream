package com.goodstart.parsing;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class ReadStream
{
	private final Reader reader;
	private boolean hasReadFirstChar = false;
	private Character nextChar;

	public ReadStream(Reader reader)
	{
		this.reader = reader;
	}

	public ReadStream(String aString)
	{
		this.reader = new StringReader(aString);
	}

	public boolean atEnd() throws IOException
	{
		try
		{
			this.peek();
		} catch (EndOfStreamException ex)
		{
			return true;
		}
		return false;
	}

	private Character getNextFromReader() throws IOException
	{
		int result = reader.read();
		if (-1 == result)
		{
			return null;
		}
		return (char) result;
	}

	public char next() throws IOException
	{
		this.readFirstCharIfNeeded();
		Character retVal = this.nextChar;
		if (this.nextChar == null)
			throw new EndOfStreamException();
		this.nextChar = this.getNextFromReader();
		return retVal;
	}

	public char peek() throws IOException
	{
		this.readFirstCharIfNeeded();
		if (this.nextChar == null)
			throw new EndOfStreamException();
		return this.nextChar;
	}

	private void readFirstCharIfNeeded() throws IOException
	{
		if (!this.hasReadFirstChar)
		{
			this.nextChar = this.getNextFromReader();
			this.hasReadFirstChar = true;
		}
	}

	public String upTo(char aCharacter) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			char nextChar = next();
			while (nextChar != aCharacter)
			{
				builder.append(nextChar);
				nextChar = next();
			}
		} catch (EndOfStreamException ex)
		{
		}
		return builder.toString();
	}

	public String upToEnd() throws IOException
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			while (true)
			{
				builder.append(next());
			}
		} catch (EndOfStreamException ex)
		{
		}
		return builder.toString();
	}
}
