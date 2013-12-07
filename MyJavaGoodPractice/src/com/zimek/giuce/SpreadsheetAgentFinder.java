package com.zimek.giuce;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Singleton;

/**
 * Singleton object which can be injected somewhere. Scope of this object is
 * annotated as @Singleton. This is scoping class to be injected.
 */
@Singleton
public class SpreadsheetAgentFinder implements AgentFinder {
	/** The type of spreadsheet we are dealing with */
	private String type;

	/** The location of the spreadsheet */
	private String path;
	
	/**
	 * This method returns an empty list of agents for compilation sake
	 * 
	 * @return An empty list of Agents
	 */
	@Override
	public List<Agent> findAllAgents() {
		List<Agent> agents = new ArrayList<>();
		// Lots of POI based implementation would go here
		return agents;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
