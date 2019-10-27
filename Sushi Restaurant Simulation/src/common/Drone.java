package common;

public class Drone extends Model{
	
	Number speed;
	String status;
	
	public Drone(String inputName, Number inputSpeed) {
		this.name = inputName;
		this.speed =inputSpeed;
		this.status = "Idle";
	}
	
	public Drone(Number speed) {
		this.name = "DRONE_TEMP";
		this.speed = speed;
		this.status = "Idle";
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public Number getSpeed() {
		return speed;
	}
	
	public String getStatus() {
		return status;
	}
	
}
