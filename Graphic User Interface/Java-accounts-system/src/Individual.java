import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Individual implements Contact{
	private String name;
	private Integer age;
	private String type;
	private String individualType;
	private Integer pay;
	private Integer GSTNumber;
	private Double totalSpend;
	private Double totalEarned;
	private LocalDate DOB;
	private ArrayList<String> productBought = new ArrayList<String>();
	private ArrayList<Double> moneySpent = new ArrayList<Double>();
	private ArrayList<Integer> payList = new ArrayList<Integer>();
	
	public Individual(String name, Integer age) {
		this.name = name;
		this.age = age;
		this.totalSpend = 0.0;
		this.totalEarned = 0.0;
		this.type = "individual";
		this.DOB = null;
		setDOB(age);
		setPayList();
		setIndividualType(); 
	}
	@Override
	  public String toString() {
	    return type + " " + individualType + ": " + name;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Integer getNumber() {
		// TODO Auto-generated method stub
		return age;
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	
	public void setIndividualType() {
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		if(randNum == 0) {
			this.individualType = "staff";
			this.pay = 0;
			setPay();
		}
		else {
			this.individualType = "customer";
			isGST();
		}
	}
	
	@Override
	public String getIndividualType() {
		// TODO Auto-generated method stub
		return individualType;
	}
	
	public void setPayList() {
		payList.add(1000);
		payList.add(2000);
		payList.add(2500);
		payList.add(3000);
	}
	
	public void setPay() {
		Random rand = new Random();
		this.pay = payList.get(rand.nextInt(payList.size()));
	}

	@Override
	public Integer getPay() {
		// TODO Auto-generated method stub
		return pay;
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
	public String getBusinessType() {
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
	
	public void setDOB(Integer age) {
		Random random = new Random();
		int year = 2022 - age;
		
		int minDay = (int) LocalDate.of(year, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(year, 12, 31).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		this.DOB = LocalDate.ofEpochDay(randomDay);
	}
	@Override
	public LocalDate getDOB() {
		// TODO Auto-generated method stub
		return DOB;
	}

}
