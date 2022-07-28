import java.util.ArrayList;
import java.util.Random;

import ecs100.UI;

public class Student {
	private String name;
	private Integer id;
	private Integer limit;
	
	private Integer totalTution;
	private Integer tutionForPoint;
	
	private Integer grade;

	private ArrayList<Course> courseEnrolled = new ArrayList<Course>();
	
	private ArrayList<String> courseList = new ArrayList<String>();
	private ArrayList<Integer> gradeList = new ArrayList<Integer>();
	private ArrayList<Integer> tutionList = new ArrayList<Integer>();
	
	private static int studentID = 0;
	private static int studentLimit = 5;
	
	public Student(String name) {
		this.name = name;
		studentID ++;
		id = studentID;
		this.limit = 5;
		setTutionList();
		this.totalTution = 0;
		this.tutionForPoint = 0;
		this.grade = 0;
	}
	
	public static Integer getStudentLimit() {
		return studentLimit;
	}
	
	public void setName(String newName) {
	    this.name = newName;
	  }
	
	public String getName() {
	    return name;
	  }
	
	public Integer getID() {
		return id;
	}
	
	public void setLimit(Integer newLimit) {
		this.limit = newLimit;
	}
	
	public Integer getLimit() {
		return limit;
	}
	
	public void generateRandomGrade() {
		Random rand = new Random();
		this.grade = rand.nextInt(100);
		gradeList.add(grade);
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public ArrayList getGradeList() {
		return gradeList;
	}
	
	public Double getAverageGrade() {
		Integer total = 0;
		for(Integer i : gradeList) {
			total += i;
		}
		if (courseEnrolled.size() == 0){
			return null;
		}
		else {
			return (double) (total/courseEnrolled.size());
		}
	}
	
//	public void setGradeList() {
//		for(Course c : courseEnrolled) {
//			generateRandomGrade();
//			gradeList.add(grade);
//		}
//	}
//	
//	public ArrayList getGradeList() {
//		return gradeList;
//	}
	
	public void setTutionList() {
		tutionList.add(100);
		tutionList.add(200);
		tutionList.add(300);
		tutionList.add(400);
		tutionList.add(500);
	}
	
	public void setTutionForPoint() {
		Random rand = new Random();
		this.tutionForPoint = tutionList.get(rand.nextInt(tutionList.size()));
	}
	
	public Integer getTutionForPoint() {
		return tutionForPoint;
	}
	
	public void setTotalTution() {
		Integer points = 0;
		for(Course c : courseEnrolled) {
			points += c.getPoint();
		}
		this.totalTution += this.tutionForPoint * points;
	}
	
	public Integer getTotalTution() {
		return totalTution;
	}
	
	public void addCourseEnrolled(Course c) {
		courseEnrolled.add(c);
		courseList.add(c.getName());
	}
	
	public ArrayList getCourseEnrolled() {
		return courseList;
	}	
	
}
