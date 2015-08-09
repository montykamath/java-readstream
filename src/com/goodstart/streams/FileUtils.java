package com.goodstart.streams;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

public class FileUtils {
	public static String readFileContents(String path) throws IOException {
		File file = new File(path);
		StringBuilder contents = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			String text = null;
			// repeat until all lines is read
			while ((text = reader.readLine()) != null) {
				contents.append(text).append(
						System.getProperty("line.separator"));
			}
		} finally {
			reader.close();
		}
		// return the file contents
		return contents.toString();
	}
}
