
public class Shipping {
	private String kurir;
	private String id;
	private String status;
	private int durasi;
	public Shipping(String kurir, String id, String status, int durasi) {
		this.kurir = kurir;
		this.id = id;
		this.status = status;
		this.durasi = durasi;
	}
	public String getKurir() {
		return kurir;
	}
	public void setKurir(String kurir) {
		this.kurir = kurir;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDurasi() {
		return durasi;
	}
	public void setDurasi(int durasi) {
		this.durasi = durasi;
	}
	
}
