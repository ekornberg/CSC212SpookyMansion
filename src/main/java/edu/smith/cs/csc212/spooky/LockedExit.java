package edu.smith.cs.csc212.spooky;

import java.util.List;

public class LockedExit extends Exit {
	
	// What's required to open the door?
	String required;
		
	public LockedExit(String target, String description, String required) {
		super(target, description);
		this.required = required;
	}
	
	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if that is OK, false if they need something special.
	 */
	@Override
	public boolean canOpen(Player player) {
		// if the player has the required item, can open exit
		if (player.myStuff.contains(required)) {
			System.out.print("I see you have a key. Let's unlock the door.");
			return true;
		} else {
			return false;
		}
	}
}