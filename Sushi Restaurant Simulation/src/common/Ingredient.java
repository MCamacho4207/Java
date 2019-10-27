package common;

public class Ingredient extends Model {
	
	protected String unit;
	protected Supplier supplier;
	protected Number restockThreshold = 10;
	protected Number restockAmount = 10;
	
	public Ingredient(String inputName, String inputUnit) {
		this.name = inputName;
		this.unit = inputUnit;
		this.supplier = null;
	}
	
	public Ingredient(String inputName, String inputUnit, Supplier inputSupplier, Number inputThreshold, Number inputAmount) {
		this.name = inputName;
		this.unit = inputUnit;
		this.supplier = inputSupplier;
		this.restockThreshold = inputThreshold;
		this.restockAmount = inputAmount;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public Number getRestockThreshold() {
		return restockThreshold;
	}
	
	public Number getRestockAmount() {
		return restockAmount;
	}
	
	public void setSupplier (Supplier givenSupplier) {
		this.supplier = givenSupplier;
	}
	
	public void setRestockValues(Number restockThreshold, Number restockAmount) {
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
}
