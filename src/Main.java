import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
	static Scanner scan = new Scanner(System.in);
	ArrayList<Product> storeList = new ArrayList<Product>();
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<RunProcess> run = new ArrayList<RunProcess>();
	
	
	public Main() {
		String name = "";
		boolean test = true;;
		int choose = 0;
		int counter = 0;
		
		init();
		
		System.out.println("Welcome to Vi Shop!^-^");
		System.out.println("To create an order, please input your name.");
		
		do {
			test = true;
			System.out.println("Name [Min. 3 characters | Must contain two words]: ");
			name = scan.nextLine();
			for(int i=0; i<name.length(); i++) {
				if(name.substring(i, i+1).equals(" ")) {
					counter++;
				}
			}
			if(!name.contains(" ") || counter > 1) {
				test = false;
				System.out.println("Name must be two words!");
			}
		} while (test==false || name.length()<=3 || counter > 1);
		
		User newUser = new User();
		userList.add(newUser);
		
		do {
			test= true;
			printCart();
			menu(name);
			try {
				choose=scan.nextInt();
			} catch (Exception e) {
				test = false;
			}
			switch(choose) {
				case 1: store();
					break;
				case 2: 
					break;
				case 3: history();
			}
		}while(test == false || choose != 4);
		
		
	}
	
	
	
	void menu(String name) {
		System.out.printf("Hello, %s!\n", name);
		System.out.println("1. Shop at Vi");
		System.out.println("2. Refresh My Shipping Status");
		System.out.println("3. View Purchase History");
		System.out.println("4. Exit");
		System.out.println(">>");	
	}
	
	
	void init() {
		int random = 0;
		String[] nama = {"The Aubree Niacinamide Serum", "Tiff Body Cacao Coffee Scrub", "Kleveru Glass Skin Serum", "Sensatia Botanicals Unscented Facial-C Serum", "Mineral Botanicals Vanila Lip Scrub", "Think Hale Let's Mask", "Faith In Face Cica Jelly Mask", "Lacoco Swallow Facial Foam", "Everwhite Brightening Essence Serum"};
		int[] harga = {99000, 150000, 143000, 180000, 55000, 129000, 29000, 165000, 125000};
		
		for(int i=0; i<9; i++) {
			random = (int)(Math.random() * 31) + 20;
			Product newProduct = new Product(nama[i], harga[i], random);
			storeList.add(newProduct);
		}
	}
	
	void store() {
		int pick = 0;
		int qty = 0;
		int price = 0;
		int ongkir = 0;
		int durasi = 0;
		int random = 0;
		String again = "";
		String alamat = "";
		String kurir = "";
		String status = "";
		boolean test = true;
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
		if(storeList.size()==0) {
			System.out.println("Sorry, all of our products are currently out of stock!");
		}
		else {
			for(int i=0; i<storeList.size(); i++) {
				System.out.println("No      : " + (i+1));
				System.out.println("Name    : " + storeList.get(i).getName());
				System.out.println("Price   : Rp. " + storeList.get(i).getPrice() + ".0");
				System.out.println("Stocks  : " + storeList.get(i).getStock());
				System.out.println();
			}
			do {
				System.out.printf("Pick product to buy [1.." + storeList.size() + "]: ");
				try {
					pick = scan.nextInt();
				} catch (Exception e) {
					test = false;
				} 
			} while (test == false || pick > storeList.size() || pick < 0);	
			
			do {
				System.out.print("Input quantity [1.." + storeList.get(pick - 1).getStock() + "]: ");
				try {
					test = true;
					qty = scan.nextInt();
				} catch (Exception e) {
					test = false;
				} 
				if(qty > storeList.get(pick-1).getStock()) {
					System.out.println("Insufficient product stock, Maximum purchase of this product is only " + storeList.get(pick-1).getStock() + ".");
					test = false;
				}
			} while (test==false || qty > storeList.size() || qty < 0);
			
			
			User user1 = new User();
			user1.addCart(storeList.get(pick-1).getName(), storeList.get(pick-1).getPrice(), qty);
			
			storeList.get(pick-1).setStock(storeList.get(pick-1).getStock()-qty);
			if(storeList.get(pick-1).getStock()==0){
				storeList.remove(pick-1);
			}
			
			do {
				test = true;
				System.out.printf("Do you want to add another product?(‘Y’/‘N’, case sensitive): ");
				again = scan.nextLine();
				if(again.contentEquals("Y")||again.contentEquals("N")) {
					test = true;
				}
				else {
					test = false;
				}
			} while (test == false);
			
			if(again.contentEquals("Y")) {
				store();
			}
			else {
				do {
					test = false;
					System.out.print("Input shipping address [must begin with 'Jl. ' (case-sensitive)]: ");
					alamat = scan.nextLine();
					if(alamat.substring(0,4).contentEquals("Jl. ")) {
						test = true;
					}
				} while (test == false);
				
				do {
					test = false;
					System.out.print("Input shipping service [VeDex | ViCepet (case-insensitive)]: ");
					kurir = scan.nextLine();
					if(kurir.equalsIgnoreCase("VeDex") || kurir.equalsIgnoreCase("ViCepet")){
						test = true;
					}
				} while (test==false);
				for(int i=0; i<user1.cartList.size(); i++) {
					price += (user1.cartList.get(i).getPrice() * user1.cartList.get(i).getQty());
				}
				System.out.println();
				System.out.println("Products' Prices        : Rp. " + price);
				if(kurir.equalsIgnoreCase("VeDex")) {
					random = (int)(Math.random() * 10)+1;
					if(random == 5) {
						status = "FAILED";
					}
					else {
						status = "On Going";
					}
					durasi = 2;
					if(price > 150000) {
						ongkir = 0;
					}
					else {
						ongkir = 10000;
					}
					System.out.println("Shipping fee            : Rp " + ongkir);
					System.out.println("Grand total             : Rp. " + (ongkir + price));
					
				}
				else {
					status = "On Going";
					durasi = 1;
					ongkir = 12000;
					System.out.println("Shipping fee            : Rp " + ongkir);
					System.out.println("Grand total             : Rp. " + (ongkir + price));
				}
				//user1.addShip(kurir ,randomUUIDString, status, durasi);
				Shipping newShip = new Shipping(kurir, randomUUIDString, status, durasi);
				userList.get(userList.size()-1).shipList.add(newShip);
				
				Thread threadProc;
				RunProcess proc = null;
				proc = new RunProcess(userList.get(userList.size()-1).shipList.get(userList.get(userList.size()-1).shipList.size()-1), userList.get(userList.size()-1).shipList.get(userList.get(userList.size()-1).shipList.size()-1).getId(), alamat, price);
				threadProc = new Thread(proc);
				run.add(proc);
				threadProc.start();
				scan.nextLine();
			}
		}
	}
		
		void printCart() {
			if(userList.get(0).shipList.size()==0) {
				System.out.println("No purchase history.");
			}
			else {
				for(int i=0; i<userList.get(0).shipList.size(); i++) {
					System.out.println(userList.get(0).shipList.get(i).getKurir());
					System.out.println("------------");
					System.out.println("Shipping ID        : " + userList.get(0).shipList.get(i).getId());
					System.out.println("Shipping Status    : " + userList.get(0).shipList.get(i).getStatus());
					if(userList.get(0).shipList.get(i).getStatus().contentEquals("FAILED")) {
						System.out.println("Sorry for the Inconvenience, your packet can't be traced.");
						System.out.println("Insurance fee will be sent directly to according address.");
					}
					System.out.println();
				}
			}
		}
		
		void history() {
			System.out.println("Finished Orders");
			System.out.println("================");
			System.out.println();
			for(int i=0; i<userList.size(); i++) {
				for(int j=0; j<userList.get(i).hisList.size(); j++) {
					System.out.println("Shipping ID        : " + userList.get(i).hisList.get(j).getId());
					System.out.println("Address            : " + userList.get(i).hisList.get(j).getAddress());
					System.out.println("Total Price        : " + userList.get(i).hisList.get(j).getPrice());
				}
			}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
