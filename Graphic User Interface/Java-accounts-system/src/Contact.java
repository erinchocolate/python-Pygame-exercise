import java.time.LocalDate;
import java.util.ArrayList;

public interface Contact {
	public String getName();
	public Integer getPay();
	public Integer getGSTNumber();
	public LocalDate getDOB();
	public Integer getNumber();//for individual, return age. for business, return business number
	public String getType();//return business or individual
	public String getIndividualType();//return staff or customer
	public String getBusinessType();//return client, supplier or partner
	public void setProductBought(String product, Double price);
	public void setProductSold(String product, Double price);
	public void setMoneySpent();
	public void setMoneyEarned();
	public Double getMoneySpent();
	public Double getMoneyEarned();
	public ArrayList<?> getProductBought();
	public ArrayList<?> getProductSold();
}
