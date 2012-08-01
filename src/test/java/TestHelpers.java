package test.java;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import main.java.Helpers;

import org.junit.Test;

public class TestHelpers {

	@Test
	public void readNames() {
		// Unsure of how to cover this well, but it's a start
		List<String> names = new ArrayList<String>();
		
		Path p1 = Paths.get("config/names.csv");
		System.out.println(p1);
		
		names = Helpers.parse(p1);
		System.out.print(names);
		System.out.print(names.get(1));
		assertNotNull(names.get(1));
		
	}

}
