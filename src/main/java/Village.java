package main.java;

import java.util.HashSet;
import java.util.Set;


public class Village {
	private Set<Resource> nodes;
	private Set<Entity> residents;
	private String name;
	
	public Village () {
		this(Config.resourcesToSpawn, Config.residentsToSpawn, Config.defaultVillageName);
	}
	
	public Village (int startingNodes, int startingResidents, String startingName) {
		nodes = new HashSet<Resource>();
		for (int i=0; i< startingNodes; ++i)
			nodes.add(new Resource());
		
		residents = new HashSet<Entity>();
		for (int i=0; i < startingResidents; ++i)
			residents.add(new Friendly());
		
		name = startingName;
	}
	
	public void addResident (Entity newResident)  {
		residents.add(newResident);
	}
	
	
	
	
	public Set<Resource> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Resource> nodes) {
		this.nodes = nodes;
	}

	public Set<Entity> getResidents() {
		return residents;
	}

	public void setResidents(Set<Entity> residents) {
		this.residents = residents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
