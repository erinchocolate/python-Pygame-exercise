import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Supplier implements Contact{
	private String name;
	private Integer businessNumber;
	private Double totalEarned;
	private Double totalSpend;
	private Integer GSTNumber;
	private String type;
	private String businessType;
	private ArrayList<String> productSold = new ArrayList<String>();
	private ArrayList<Double> moneyEarned = new ArrayList<Double>();

	public Supplier(String name, Integer nbn) {
		this.name = name;
		this.businessNumber = nbn;
		this.totalEarned = 0.0;
		this.totalSpend = 0.0;
		this.type = "business";
		this.businessType = "supplier";
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

	}

	@Override
	public void setProductSold(String product, Double price) {
		// TODO Auto-generated method stub
		productSold.add(product);
		moneyEarned.add(price);
	}
	
	@Override
	public void setMoneySpent() {

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
		return null;
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
