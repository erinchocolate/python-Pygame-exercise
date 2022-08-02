import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Client implements Contact{
	private String name;
	private Integer businessNumber;
	private Double totalSpend;
	private Double totalEarned;
	private Integer GSTNumber;
	private String type;
	private String businessType;
	private ArrayList<String> productBought = new ArrayList<String>();
	private ArrayList<Double> moneySpent = new ArrayList<Double>();

	public Client(String name, Integer nbn) {
		this.name = name;
		this.businessNumber = nbn;
		this.totalSpend = 0.0;
		this.totalEarned = 0.0;
		this.type = "business";
		this.businessType = "client";
		isGST();
	}
	
	@Override
	  public String toString() {
	    return type + " " + businessType + ": " + name;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Integer getNumber() {
		// TODO Auto-generated method stub
		return businessNumber;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	@Override
	public String getBusinessType() {
		// TODO Auto-generated method stub
		return businessType;
	}

	@Override
	public void setProductBought(String product, Double price) {
		// TODO Auto-generated method stub
		productBought.add(product);
		moneySpent.add(price);
	}
	
	@Override
	public void setProductSold(String product, Double price) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMoneySpent() {
		for(Double s : moneySpent) {
			this.totalSpend += s;
		}
	}
	
	@Override
	public void setMoneyEarned() {
	}

	@Override
	public Double getMoneySpent() {
		// TODO Auto-generated method stub
		return totalSpend;
	}

	@Override
	public Double getMoneyEarned() {
		// TODO Auto-generated method stub
		return totalEarned;
	}

	@Override
	public ArrayList getProductBought() {
		// TODO Auto-generated method stub
		return productBought;
	}

	@Override
	public ArrayList getProductSold() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIndividualType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPay() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void isGST() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		if (num == 0) {
			setGSTNumber();
		}
		else {
			this.GSTNumber = null;
		}
	}
	
	public void setGSTNumber() {
		Random rand = new Random();
		this.GSTNumber = 0;
		this.GSTNumber = 100000 + rand.nextInt(900000);
	}

	@Override
	public Integer getGSTNumber() {
		// TODO Auto-generated method stub
		return GSTNumber;
	}

	@Override
	public LocalDate getDOB() {
		// TODO Auto-generated method stub
		return null;
	}



}
