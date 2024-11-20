
public class Exit {
	private Location destination;
	private String name;
	private String shortDescription;

	public Exit(Location destination, String name, String shortDescription) {
		this.destination = destination;
		this.name = name;
		this.shortDescription = shortDescription;
	}

	public Location getDestination() {
		return destination;
	}

	public String getName() {
		return name;
	}

	public String getShortDescription() {
		return shortDescription;
	}
}
