
public class History {

	private String id;
	private String address;
	private int price;
	public History(String id, String address, int price) {
		this.id = id;
		this.address = address;
		this.price=price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	
}
