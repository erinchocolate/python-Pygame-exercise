import java.util.ArrayList;
import java.util.Random;

import ecs100.UI;

public class Lecturer {
	private String name;
	private Integer id;
	private Integer pay;
	private Integer limit;
	
	private ArrayList<Course> courseTeached = new ArrayList<Course>();
	private ArrayList<String> courseList = new ArrayList<String>();
	private ArrayList<Integer> paySlip = new ArrayList<Integer>();
	
	private static int lecturerID = 0;
	
	public Lecturer(String name) {
		this.name = name;
		lecturerID ++;
		this.id = lecturerID;
		this.limit = 6;
		setPaySlip();
		setPay();
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLimit(Integer newLimit) {
		this.limit = newLimit;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public void setPaySlip() {
		paySlip.add(15000);
		paySlip.add(25000);
		paySlip.add(35000);
		paySlip.add(45000);
		paySlip.add(55000);
	}
	
	public void setPay() {
		Random rand = new Random();
		this.pay = paySlip.get(rand.nextInt(paySlip.size()));
	}
	
	public Integer getPay() {	
		return pay;
	}
	
	public void addCourseTeached(Course course) {
		courseTeached.add(course);
		courseList.add(course.getName());
	}
	
	public ArrayList getCourseTeached() {
		return courseList;
	}
	
}
