package com.zimek.giuce;

import com.google.inject.Provider;

public class AgentFinderProvider implements Provider<AgentFinder> {

	@Override
	public AgentFinder get() {
		SpreadsheetAgentFinder finder = new SpreadsheetAgentFinder();
		finder.setType("Excel 97");
		finder.setPath("c:/temp/agents.xls");
		return finder;
	}

}
