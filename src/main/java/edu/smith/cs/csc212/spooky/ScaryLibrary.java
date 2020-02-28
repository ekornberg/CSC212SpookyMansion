package edu.smith.cs.csc212.spooky;
	
	import java.util.HashMap;
	import java.util.Map;

	/**
	 * Scary Library, the game.
	 *
	 */
	public class ScaryLibrary implements GameWorld {
		private Map<String, Place> places = new HashMap<>();

		/**
		 * Where should the player start?
		 */
		@Override
		public String getStart() {
			return "lobby";
		}

		/**
		 * This constructor builds our ScaryLibrary game.
		 */
		public ScaryLibrary() {
			Place lobby = insert(Place.create("lobby", 
					"You are in the lobby of a large, old building.\n"
					+ "The front doors are locked. How did you get here?"));
			lobby.addExit(new Exit("cellar", "There are stairs leading down."));
			lobby.addExit(new Exit("fiction", "There are stairs leading up."));
			lobby.addExit(new Exit("circulation", "There is a wide door."));
			lobby.addExit(new Exit("reading", "There is a narrow door."));

			String EMOJI_SKULL = "\uD83D\uDC80";
			Place reading = insert(Place.create("reading", 
					"On the bottom shelf, you see scratched a series"
					+ " of letters and a skull icon ("+EMOJI_SKULL+").\n"
					+ "East.. South.. West.. South.\n" 
					+ "What could it mean?"));
			reading.addExit(new Exit("lobby", "Go back."));

			Place cellar = insert(
					Place.create("cellar", "You have found the cellar of the library.\n"
							+ "It is darker down here.\n" + "You get the sense a secret is nearby,"
							+ " but you only see the stairs you came from."));
			cellar.addExit(new Exit("lobby", "There are stairs leading up."));
			cellar.addExit(new Exit("sinkhole", "There is a sunken hole in the floor that you could climb into..."));
			cellar.addExit(new Exit("history", "There is a history section filled with biographies and mysteries of the past."));		

			Place history = insert(Place.create("history", "There is a section of books with biographies and mysteries of the past."));
			history.addExit(new Exit("cellar", "You see a way to the cellar."));
			
			Place fallingsinkhole = insert(
					Place.create("sinkhole", "I don't know what you were thinking..."));
			fallingsinkhole.addExit(new Exit("tunnel0", "Keep falling."));

			Place antique = insert(Place.create("antique",
					"Something rustles in the rafters as you enter the antique section. Creepy.\n"));
			antique.addExit(new Exit("lobby", "There are stairs leading down."));
			antique.addExit(new Exit("fiction", "There is more through an archway."));

			Place fiction = insert(Place.create("fiction", "You're in the fiction section. There are tales of unicorns and fairytales here.\n"
					+ "This part of the attic is brighter, so maybe you're safe here."));
			fiction.addExit(new Exit("fiction", "There is more back through the archway."));
			fiction.addExit(new Exit("balcony", "There is a balcony."));
			fiction.addExit(new Exit("spiral", "There is a spiral staircase."));
			fiction.addExit(new Exit("rare", "There is a section of books you've never heard of before. They seem to be rare books. Hmm."));
			// The secret exit is here.
			fiction.addExit(new SecretExit("secretExit", "You found a secret exit!"));
			
			Place rare = insert(Place.create("rare", "There are many books you've never even heard of. They seem to be rare books."));
			rare.addExit(new Exit("antique", "There's more beyond this area."));
			// Key to unlock main exit.
			rare.addItem("key");

			Place balcony = insert(Place.create("balcony", "The night is pitch-black."));
			balcony.addExit(new Exit("antique", "Return to the fiction section."));
			balcony.addExit(new Exit("jump", "You could jump off, but you can't see the ground."));

			Place jump = insert(Place.terminal("jump", "I wonder what you expected to happen here."));
			
			Place circulation = insert(Place.create("circulation", "You've found the circulation desk./n" +
							"There are cobwebs and spiders. You wonder if anyone has been here in a while."));
			circulation.addExit(new Exit("lobby", "There is a wide door."));
			circulation.addExit(new Exit("spiral", "There is a spiral staircase."));

			Place spiral = insert(Place.create("spiral", "You make your way to a spiral staircase. What are you doing?"));
			spiral.addExit(new Exit("science", "Walk to the bottom of the stairs."));
			spiral.addExit(new Exit("circulation", "Walk to the landing in the middle."));
			spiral.addExit(new Exit("antique", "Walk to the top of the stairs."));

			Place science = insert(Place.create("science", "You have found the science section."));
			science.addExit(new Exit("tunnel0", "There is door with a skull on it... "+EMOJI_SKULL));
			science.addExit(new Exit("hallway0", "There is a long hallway."));
			science.addExit( new Exit("cellar", "Head to the cellar."));

			// Hallway length
			int hallwayDepth = 4;
			int lastHallwayPart = hallwayDepth - 1;
			for (int i = 0; i < hallwayDepth; i++) {
				Place hallwayPart = insert(Place.create("hallway" + i, "This is a very long hallway."));
				if (i == 0) {
					hallwayPart.addExit(new Exit("science", "Go back."));
				} else {
					hallwayPart.addExit(new Exit("hallway" + (i - 1), "Go back."));
				}
				// If not in last part of hall, can keep going forward.
				if (i != lastHallwayPart) {
					hallwayPart.addExit(new Exit("hallway" + (i + 1), "Go forward.\n" +
							"You feel the number " + (i + 1) + " scratched into the wall."));
				} else {
					// Locked Exit
					hallwayPart.addExit(new LockedExit("crypt", "There is an old wooden door with a worn lock.", "key"));
				}
			}
			
			// Main exit
			Place crypt = insert(Place.terminal("crypt", "You have found the crypt.\n"
					+ "It is scary here, but there is an exit to outside.\n" +
					"Maybe you'll be safe out there."));
			
			// The secret exit
			Place secretExit = insert(Place.terminal("secretExit", "You have found the crypt.\n"
					+ "It is scary here, but there is an exit to outside.\n" + "Maybe you'll be safe out there."));			
			secretExit.addExit(new Exit("crypt", "There is darkness ahead."));
			
			String labyrinthDescription = "You see four hallways stretching out into the mist.\n"
					+ "On the ground, there is tile shaped like a compass.";
			Place tunnel0 = insert(Place.create("tunnel0", labyrinthDescription));
			Place tunnel1 = insert(Place.create("tunnel1", labyrinthDescription));
			Place tunnel2 = insert(Place.create("tunnel2", labyrinthDescription));
			Place tunnel3 = insert(Place.create("tunnel3", labyrinthDescription));
			
			// solution: East.
			tunnel0.addExit(new Exit("tunnel0", "Go North."));
			tunnel0.addExit(new Exit("tunnel1", "Go East."));
			tunnel0.addExit(new Exit("tunnel0", "Go South."));
			tunnel0.addExit(new Exit("tunnel0", "Go West."));
			
			// solution: South.
			tunnel1.addExit(new Exit("tunnel0", "Go North."));
			tunnel1.addExit(new Exit("tunnel0", "Go East."));
			tunnel1.addExit(new Exit("tunnel2", "Go South."));
			tunnel1.addExit(new Exit("tunnel0", "Go West."));
			
			// solution: West.
			tunnel2.addExit(new Exit("tunnel0", "Go North."));
			tunnel2.addExit(new Exit("tunnel0", "Go East."));
			tunnel2.addExit(new Exit("tunnel0", "Go South."));
			tunnel2.addExit(new Exit("tunnel3", "Go West."));
			
			// solution: South.
			tunnel3.addExit(new Exit("tunnel0", "Go North."));
			tunnel3.addExit(new Exit("tunnel3", "Go East."));
			tunnel3.addExit(new Exit("lobby", "Go South."));	
			tunnel3.addExit(new Exit("tunnel0", "Go West."));
			
			// Make sure your graph makes sense!
			checkAllExitsGoSomewhere();
		}

		/**
		 * This helper method saves us a lot of typing. We always want to map from p.id
		 * to p.
		 * 
		 * @param p - the place.
		 * @return the place you gave us, so that you can store it in a variable.
		 */
		private Place insert(Place p) {
			places.put(p.getId(), p);
			return p;
		}
		
		/**
		 * I like this method for checking to make sure that my graph makes sense!
		 */
		private void checkAllExitsGoSomewhere() {
			boolean missing = false;
			// For every place:
			for (Place p : places.values()) {
				// For every exit from that place:
				for (Exit x : p.getVisibleExits()) {
					// That exit goes to somewhere that exists!
					if (!places.containsKey(x.getTarget())) {
						// Don't leave immediately, but check everything all at once.
						missing = true;
						// Print every exit with a missing place:
						System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
					}
				}
			}
			// Now that we've checked every exit for every place, crash if we printed any
			// errors.
			if (missing) {
				throw new RuntimeException("You have some exits to nowhere!");
			}
		}
		/**
		 * Get a Place object by name.
		 */
		public Place getPlace(String id) {
			return this.places.get(id);
		}
}
