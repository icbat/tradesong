package main.java.engine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Helpers {

	public static List<String> parse (Path filename) {
		List<String> output = new ArrayList<String>();
		try {
			output = Files.readAllLines(filename, Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}