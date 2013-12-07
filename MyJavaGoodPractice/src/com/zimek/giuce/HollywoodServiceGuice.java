package com.zimek.giuce;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.inject.name.Named;

/**
 * Here AgentFinder object will be injected into the constructor.
 * @Named annotation indicates that AgentFinder called primary will be injected.
 * The AgentFinder to be injected is defined in {@link AgentFinderModule}.
 * 
 * @author zimek
 *
 */
public class HollywoodServiceGuice {
	private AgentFinder finder = null;

	@Inject 
	public HollywoodServiceGuice(@Named("primary") AgentFinder agentFinder) {
		this.finder = agentFinder;
	}

	public List<Agent> getFriendlyAgents() {
		List<Agent> agents = finder.findAllAgents();
		List<Agent> friendlyAgents = filterAgents(agents, "Java Developers");
		return friendlyAgents;
	}

	public List<Agent> filterAgents(List<Agent> agents, String agentType) {
		List<Agent> filteredAgents = new ArrayList<>();
		for (Agent agent : agents) {
			if (agent.getType().equals("Java Developers")) {
				filteredAgents.add(agent);
			}
		}
		return filteredAgents;
	}

}
