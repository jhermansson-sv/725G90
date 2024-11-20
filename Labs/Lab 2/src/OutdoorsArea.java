import java.util.ArrayList;

public class OutdoorsArea extends Location {
	ArrayList weather;
	private boolean hasDigged;
	private String digMessage;

	OutdoorsArea(String name, String shortDescription, String description, ArrayList weather, String digMessage) {
		super(name, shortDescription, description);
		this.weather = weather;
		this.hasDigged = false;
		this.digMessage = digMessage;
	}

	@Override
	public boolean getIfOutside() {
		return true;
	}

	@Override
	public void setHasDigged(boolean hasDigged) {
		this.hasDigged = true;
	}

	public boolean getHasDigged() {
		return hasDigged;
	}

	@Override
	public boolean getDiggable() {
		return true;
	}

	@Override
	public String getDigMessage() {
		return digMessage;
	}
}
