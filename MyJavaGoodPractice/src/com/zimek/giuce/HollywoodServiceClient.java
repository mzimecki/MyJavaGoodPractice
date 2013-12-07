package com.zimek.giuce;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Builds a Guice object graph which means gets injector 
 * basing on module (configuration)
 * 
 * @author zimek
 *
 */
public class HollywoodServiceClient {
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AgentFinderModule());
		HollywoodServiceGuice hollywoodService = injector.getInstance(HollywoodServiceGuice.class);
		List<Agent> agents = hollywoodService.getFriendlyAgents();
		// Do stuff with agents.
	}

}
