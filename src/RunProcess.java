
public class RunProcess implements Runnable {
	private String id;
	private String address;
	private int price;
	private int durasi;
	User user1 = new User(); 
	 
	public RunProcess(Shipping shipping, String id, String address, int price){
		 this.durasi = shipping.getDurasi();
		 this.id = id;
		 this.address = address;
		 this.price = price;
	}
	 	
	@Override
	public void run() {
		for (int i = this.durasi; i>=0; i--) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.durasi = i;
		}
		History newHis = new History(id, address, price);
		user1.hisList.add(newHis);
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

	public int getDurasi() {
		return durasi;
	}

	public void setDurasi(int durasi) {
		this.durasi = durasi;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}


	

}
