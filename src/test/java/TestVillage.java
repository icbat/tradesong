package test.java;

import static org.junit.Assert.*;

import main.java.Config;
import main.java.Village;

import org.junit.Test;

public class TestVillage {

	@Test
	public void initialize() {
		Village alpha = new Village();
		assertEquals(alpha.getNodes().size(), Config.resourcesToSpawn);
		
		Village beta = new Village(7, 8, "Beta Beta Potata");
		assertEquals(beta.getNodes().size(), 7);
		
		assertEquals(alpha.getName(), "New Village");
		assertEquals(beta.getName(), "Beta Beta Potata");
	}
}