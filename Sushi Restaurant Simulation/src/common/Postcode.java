package common;

public class Postcode extends Model{
	
	protected String postcode;
	protected Number distance;
	
	public Postcode(String code) {
		this.postcode = code;
	}
	
	public Postcode(String code, Number distance) {
		this.postcode = code;
		this.distance = distance;
	}
	
	@Override
	public String getName() {
		return postcode;
	}

	public Number getDistance() {
		return distance;
	}

}
