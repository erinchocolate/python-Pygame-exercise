import java.util.ArrayList;
import java.util.Random;

import ecs100.UI;

public class Course {
	private String name;
	private String id;
	private Integer point;
	private Integer limit;
	private Integer profit;
	private Integer income;
	private Integer roomCost;
	private Integer lecturerCost;
	
	private ArrayList<Integer> pointList = new ArrayList<Integer>();
	private ArrayList<Integer> gradeList = new ArrayList<Integer>();
	
	private ArrayList<String> studentList = new ArrayList<String>();
	private ArrayList<String> lecturerList = new ArrayList<String>();
	
	private ArrayList<Student> studentEnrolled = new ArrayList<Student>();
	private ArrayList<Lecturer> lecturerRegistered = new ArrayList<Lecturer>();
	
	private static Integer courseID = 0;
	
	public Course(String n) {
		this.name = n;
		courseID ++;
		this.id = courseID.toString();
		this.limit = 30;
		this.roomCost = 300;
		this.lecturerCost = 0;
		this.income = 0;
		this.profit = 0;
		setPointList();
	}
	
	public void setName(String newName) {
	    this.name = newName;
	  }
	
	public String getName() {
	    return name;
	  }
	
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public void setPointList() {
		pointList.add(15);
		pointList.add(20);
		pointList.add(30);
		pointList.add(40);
		pointList.add(45);
		pointList.add(60);
	}
	
	public void setPoint() {
		Random rand = new Random();
		point = pointList.get(rand.nextInt(pointList.size()));
	}
	
	public Integer getPoint() {
		return point;
	}
	
	public void setRoomCost(Integer cost) {
		this.roomCost = cost;
	}
	
	public Integer getRoomCost() {
		return roomCost;
	}
	
	public void addGrade(Integer grade) {
		gradeList.add(grade);
	}
	
	public ArrayList getGradeList() {
		return gradeList;
	}
	
	public Double getAverageGrade() {
		Integer total = 0;
		for(Integer i : gradeList) {
			total += i;
		}
		return (double) (total/studentEnrolled.size());
	}
	
	public Integer getLecturerCost() {
		return lecturerCost;
	}
	
	public void addLecturer(Lecturer l) {
		lecturerRegistered.add(l);
		lecturerCost += l.getPay();
		lecturerList.add(l.getName());
	}
	
	public ArrayList getLecturerList() {
		return lecturerList;
	}
	
	public void addEnrolledStudent(Student s) {
		studentEnrolled.add(s);
		studentList.add(s.getName());
	}
	
	public ArrayList getEnrolledStudent() {
		return studentList;
	}
	
	public void setCourseIncome() {
		for(Student s: studentEnrolled) {
			this.income += s.getTutionForPoint() * this.point;
		}
	}
	
	public Integer getCourseIncome() {
		return income;
	}
	
	public void setCourseProfit() {
		this.profit = this.income - this.lecturerCost - this.roomCost;
	}
	
	public Integer getCourseProfit() {
		return profit;
	}
	
}
