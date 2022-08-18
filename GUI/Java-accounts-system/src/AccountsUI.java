import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ecs100.UI;

public class AccountsUI {
	
	private List<Contact> contacts = new ArrayList<>();
	private List<Individual> staff = new ArrayList<>();
	private List<Individual> personalCustomer = new ArrayList<>();
	private List<String> products = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private List<String> partners = new ArrayList<>();
	private List<Double> transactions = new ArrayList<>();
	
	public AccountsUI() {
		UI.initialise();
		UI.addButton("Add individual contact", this::addPerson);
		UI.addButton("Add business supplier contact", this::addSupplier);
		UI.addButton("Add business client contact", this::addBusinessClient);
		UI.addButton("Add trading partner contact", this::addTradingPartner);
		UI.addButton("List contacts", this::listContacts);
		UI.addButton("Find contact by name", this::findContact);
		UI.addButton("Record purchase from supplier", this::recordPurchase);
		UI.addButton("Record sale to client", this::recordSale);
		UI.addButton("Report sale/purchase balance for contact", this::reportBalance);
		UI.addButton("Compute total profit", this::computeProfit);
		UI.addButton("List all transactions", this::listTransactions);
		UI.addButton("Find transaction by contact name", this::searchTransactionsByName);
		UI.addButton("Find transaction by threshold", this::searchTransactionsByNumber);		
	}
	
	private void addPerson() {
		String name = UI.askString("Name: ");
		int age = UI.askInt("Age: ");
		Individual individual = new Individual(name, age);
		contacts.add(individual);
		if(checkStaff(individual)) {
			staff.add(individual);
		}
		else {
			personalCustomer.add(individual);
		}
	}
	
	private void addSupplier() {
		// Add a supplier to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		Supplier supplier = new Supplier(name, nbn);
		contacts.add(supplier);
	}
	
	private void addBusinessClient() {
		// Add a business client to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		
		Client client = new Client(name, nbn);
		contacts.add(client);
	}
	
	private void addTradingPartner() {
		// Add someone who is both a buyer and a seller to the list of contacts
		String name = UI.askString("Name: ");		
		Partner partner = new Partner(name);
		contacts.add(partner);
	}
	
	private void listContacts() {
		// List all contacts in the system
		UI.clearPanes();
		UI.println("Here is the contact list:");
		for(Contact c: contacts) {
			UI.println(c);
		}
	}
	
	private Contact getContact(String name) {
		Contact contact = null;
		for(Contact c: contacts) {
			if(c.getName().equals(name)) {
				contact = c;
			}
		}
		return contact;
	}
	

	
	private void findContact() {
		// Find one of the contacts by name and report on them
		UI.clearPanes();
		String name = UI.askString("Name: ");
		if(getContact(name) == null) {
			UI.println("The contact you search for doesn't exsit!");
		}
		else {
			getContactInfo(getContact(name));	
		}
	}
	
	private void getContactInfo(Contact c) {
		
		if (c.getType().equals("individual")) {
			getIndividualContactInfo(c);
		}
		else {
			getBusinssContactInfo(c);
		}
	}
	
	private Boolean checkStaff(Contact c) {
		boolean isStaff = false;
		if(c.getIndividualType().equals("staff")) {
			isStaff = true;
		}
		return isStaff;
	}
	
	private void getIndividualContactInfo(Contact c) {
		UI.clearPanes();
		UI.println("Name: "+ c.getName());
		UI.println("Birthday: " + c.getDOB());
		if(checkStaff(c)) {
			UI.println("Type: " + c.getIndividualType());
			UI.println("Salary: " + c.getPay());
		}
		else {
			UI.println("Type: " + c.getIndividualType());
			UI.println("Purchased product list: " + c.getProductBought());
			UI.println("Money spent: " + c.getMoneySpent());
			UI.println("GST Number: " + c.getGSTNumber());
		}
	}
	
	private void getBusinssContactInfo(Contact c) {
		UI.clearPanes();
		UI.println("Name: "+ c.getName());
		UI.println("GST Number: " + c.getGSTNumber());
		UI.println("Business number: " + c.getNumber());
		if (c.getBusinessType().equals("client")) {
			UI.println("Purchased product list: " + c.getProductBought());
			UI.println("Money spent: " + c.getMoneySpent());
		}
		else if (c.getBusinessType().equals("supplier")) {
			UI.println("Sold product list: " + c.getProductSold());
			UI.println("Money earned: " + c.getMoneyEarned());
		}
		else {
			UI.println("Purchased product list: " + c.getProductBought());
			UI.println("Money spent: " + c.getMoneySpent());
			UI.println("Sold product list: " + c.getProductSold());
			UI.println("Money earned: " + c.getMoneyEarned());
		}
	}
	
	private void recordPurchase() {
		Contact contact;
		// Record a purchase from a supplier
		String name = UI.askString("Supplier: ");
		String product = UI.askString("Purchased: ");
		Double price = UI.askDouble("Price: ");
		if(getContact(name) == null) {
			UI.println("Please add this supplier first.");
		}
		else {
			contact = getContact(name);
			contact.setProductSold(product, price);
			products.add(product);
			types.add("Purchase");
			partners.add(contact.getName());
			transactions.add(price);
		}
		
	}
	
	private void recordSale() {
		Contact contact;
		// Record a sale to a customer
		String name = UI.askString("Customer: ");
		String product = UI.askString("Purchased: ");
		Double price = UI.askDouble("Price: ");
		if(getContact(name) == null) {
			UI.println("Please add this client first.");
		}
		else {
			contact = getContact(name);
			contact.setProductBought(product, price);
			products.add(product);
			types.add("Sale");
			partners.add(contact.getName());
			transactions.add(price);
		}
		
	}
	
	
	private void reportBalance() {
		Contact contact;
		// Report how much has been paid by/to a contact
		String name = UI.askString("Name: ");
		if(getContact(name) == null) {
			UI.println("Please add this contact first.");
		}
		else {
			contact = getContact(name);
			getBalance(contact); 
		}
	}
	
	private void getBalance(Contact c) {
		c.setMoneySpent();
		c.setMoneyEarned();
		UI.println("Money spent: " + c.getMoneySpent());
		UI.println("Money earned: " + c.getMoneyEarned());
	}
	
	private void computeProfit() {
		// Compute the total profit of the business (sales - purchases)
		Double sales = 0.0;
		Double purchase = 0.0;
		Double profit = 0.0;
		Double labourCost = 0.0;
		for(Contact c: contacts) {
			c.setMoneyEarned();
			c.getMoneySpent();
			sales += c.getMoneySpent();
			purchase += c.getMoneyEarned();
		}
		
		for(Individual i:staff) {
			labourCost += i.getPay();
		}
		
		profit = sales - purchase - labourCost;
		UI.println("The profit of business is " + profit);
	}
	
	private void listTransactions() {
		UI.clearPanes();
		for(int i = 0; i < transactions.size() ; i++) {
			UI.println("Here is the list of all transactions:");
			UI.println("Product: " + products.get(i));
			UI.println("Price: " + transactions.get(i));
			UI.println("Type: " + types.get(i));
			UI.println("Customer/Supplier: " + partners.get(i));
			UI.println("-------------");
		}
	}	
	
	private void searchTransactionsByName() {
		UI.clearPanes();
		String name = UI.askString("Name: ");
		if(getContact(name) == null) {
			UI.println("The contact you search for doesn't exsit.");
		}
		else if(!partners.contains(name))
		{
			UI.println("The contact you search for doesn't have transactions.");
		}
		else {
			UI.println("Here is the list of all transactions with: " + name);
			getTransactionsByName(name);
		}
	}
	
	private void getTransactionsByName(String name) {
		for(int i = 0; i < transactions.size() ; i++) {
			if(partners.get(i).equals(name)) {
				UI.println("Product: " + products.get(i));
				UI.println("Price: " + transactions.get(i));
				UI.println("Type: " + types.get(i));
				UI.println("-------------");
			}
		}
	}
	
	private void searchTransactionsByNumber() {
		UI.clearPanes();
		Integer threshold = UI.askInt("Threshold: ");
		if (threshold >= Collections.max(transactions)) {
			UI.println("There is no transactions over: " + threshold);
		}
		else {
			UI.println("Here is the list of transactions over: " + threshold);
			getTransactionsByNumber(threshold);
		}
	}
	
	private void getTransactionsByNumber(Integer threshold) {
		for(int i = 0; i < transactions.size() ; i++) {
			if(transactions.get(i) > threshold) {
				UI.println("Product: " + products.get(i));
				UI.println("Price: " + transactions.get(i));
				UI.println("Type: " + types.get(i));
				UI.println("Customer/Supplier: " + partners.get(i));
				UI.println("-------------");
			}
		}
	}
	
	public static void main(String[] args) {
		new AccountsUI();
	}

}
