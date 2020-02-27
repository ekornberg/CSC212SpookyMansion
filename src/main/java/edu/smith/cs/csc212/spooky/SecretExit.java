package edu.smith.cs.csc212.spooky;

import java.util.List;

public class SecretExit extends Exit{
	
	// Secret exit is hidden
	private boolean hidden = true;
	
	public SecretExit(String target, String description) {
		super(target, description);
	}
	
	// When user searches room, exit is no longer hidden
	@Override 
	public void search() {
		hidden = false;		
	}
	
	// Secret exit is secret when hidden
	@Override 
	public boolean isSecret() { 
		return hidden; 
		}	
}
