import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Partner implements Contact{
	private String name;
	private Double totalSpent;
	private Double totalEarned;
	private String type;
	private String businessType;
	private Integer GSTNumber;
	private ArrayList<String> productBought = new ArrayList<String>();
	private ArrayList<Double> moneySpent = new ArrayList<Double>();
	private ArrayList<String> productSold = new ArrayList<String>();
	private ArrayList<Double> moneyEarned = new ArrayList<Double>();
	
	public Partner(String name) {
		this.name = name;
		totalSpent = 0.0;
		totalEarned = 0.0;
		this.type = "business";
		this.businessType = "trading partner";
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
		return null;
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
		productSold.add(product);
		moneyEarned.add(price);
	}
	
	@Override
	public void setMoneySpent() {
		for(Double s : moneySpent) {
			this.totalSpent += s;
		}
	}
	
	@Override
	public void setMoneyEarned() {
		for(Double s : moneyEarned) {
			this.totalEarned += s;
		}
	}

	@Override
	public Double getMoneySpent() {
		// TODO Auto-generated method stub
		return totalSpent;
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
		return productSold;
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
