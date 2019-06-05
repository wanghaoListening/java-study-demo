package com.haothink.designpattern.mm.command;

import java.util.ArrayList;
import java.util.List;

public class Boy {
	
	private String name;
	private List<Command> commands = new ArrayList<Command>();
	
	public void pursue(MM mm){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean addCmd(Command cmd){
		return this.commands.add(cmd);
	}
	
	public void executeCmd(){
		for(Command command : commands){
			command.excute();
		}
	}
	
	
}
