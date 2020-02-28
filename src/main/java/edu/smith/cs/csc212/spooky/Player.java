package edu.smith.cs.csc212.spooky;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;

	// Places that player has visited.
	private Set<String> visited;
	
	// List of player's items
	public List<String> myStuff;
	
	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<>();
		this.myStuff = new ArrayList<>();
	}
	
	// What items does player have?
	public void stuff() {
		// If player doesn't have any items
		if (!haveStuff()) {
			System.out.println("You have nothing.");
		} else {
			System.out.print("You have: ");
			for (String item : myStuff)
				System.out.print(item + "; ");
		}
	}
	
	// Does player have stuff?
	private boolean haveStuff() {
		if (myStuff.isEmpty()) {
			return false;
		} else {
			return true;
		}		
	}
	
	// When player takes item, add it to list of player's stuff
	public void keep(List<String> roomStuff) { 
		myStuff.addAll(roomStuff);
	}
	
	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * This adds the place to the player's memory.
	 */
	public void rememberThisPlace() {
		this.visited.add(place);
	}
	
	/**
	 * This checks the player's memory
	 * @return true if we have been here before.
	 */
	
	public boolean hasBeenHereBefore() {
		return this.visited.contains(this.getPlace());
	}
	
	/**
	 * Call this method when the player moves to a new place.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.place = place;
	}
}
