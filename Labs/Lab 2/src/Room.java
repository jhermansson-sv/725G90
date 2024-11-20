public class Room extends Location {
	private String pathType;
	private boolean hasLight;

	Room(String name, String shortDescription, String description, int size, Boolean hasLight) {
		super(name, shortDescription, description);
		this.pathType = "door";
		this.hasLight = hasLight;
	}

	public String getPathType() {
		return this.pathType;
	}

	public boolean getHasLight(Location location) {
		return this.hasLight;
	}

	public boolean setHasLight() {
		return this.hasLight = true;
	}
}