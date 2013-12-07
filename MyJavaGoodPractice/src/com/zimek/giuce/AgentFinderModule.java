package com.zimek.giuce;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletScopes;

/**
 * Guice configuration class (module). Defines linked bindings with binding
 * annotations. Configuration of the dependencies which can be injected.
 * 
 * Uses @Provides which indicates that specific AgentFinder will be used.
 * It will be already instantiated object.
 */
public class AgentFinderModule extends AbstractModule {
	
	/**
	 * Defines binding with annotations (additional names, ids).
	 */
	@Override
	protected void configure() {
		bind(AgentFinder.class)
		.annotatedWith(Names.named("primary"))
		.to(WebServiceAgentFinder.class)
		.in(Singleton.class); //another way of defining a scope of object to be injected
		
		//This binding can be done via AgentFinderProvider = implementation of Provider<T>
		//or via provideAgentFinder() method like below = @Provides.
		//bind(AgentFinder.class).toProvider(AgentFinderProvider.class);
	}
	
	/**
	 * Other way of binding bind().toProvider() with Request scope of
	 * object to be injected.
	 * 
	 * @return
	 */
	@Provides //@RequestScoped
	AgentFinder provideAgentFinder() {
		SpreadsheetAgentFinder finder = new SpreadsheetAgentFinder();
		finder.setType("Excel 97");
		finder.setPath("c:/temp/agents.xls");
		return finder;
	}

}
