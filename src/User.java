import java.util.ArrayList;

public class User {
	String name;
	ArrayList<UserCart> cartList = new ArrayList<UserCart>();
	ArrayList<Shipping> shipList = new ArrayList<Shipping>();
	ArrayList<History> hisList = new ArrayList<History>();
	public User() {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addCart(String name, int price, int qty) {
		UserCart newList = new UserCart(name, price, qty);
		cartList.add(newList);
	}
	
	public void addShip(String kurir, String id, String status, int durasi) {
		Shipping newShip = new Shipping(kurir, id, status, durasi);
		shipList.add(newShip);
	}
}
