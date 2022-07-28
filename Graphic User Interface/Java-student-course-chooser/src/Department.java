import java.util.ArrayList;

public class Department {
	private String name;
	private Integer id;
	private Double averageGPA;
	
	private ArrayList<Course> courseOffered = new ArrayList<Course>();
	private ArrayList<Student> studentEnrolled = new ArrayList<Student>();
	
	private ArrayList<String> courseList = new ArrayList<String>();
	private ArrayList<String> studentList = new ArrayList<String>();
	
	private ArrayList<Double> courseGradeList = new ArrayList<Double>();
	private ArrayList<Course> highGPACourse = new ArrayList<Course>();
	private ArrayList<Course> lowGPACourse = new ArrayList<Course>();

	private static int departmentID = 0;
	
	public Department(String name) {
		this.name = name;
		departmentID ++;
		this.id = departmentID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getID() {
		return id;
	}
	
	public void addCourseOffered(Course c) {
		courseOffered.add(c);
		courseList.add(c.getName());
	}
	
	public void addStudentEnrolled(Student s) {
		studentEnrolled.add(s);
		studentList.add(s.getName());
	}
	
	public ArrayList getCourseOffered() {
		return courseList;
	}
	
	public ArrayList getStudentEnrolled() {
		return studentList;
	}
	
//	public ArrayList getCourseOffered() {
//		for (Course c : courseOffered) {
//			courseList.add(c.getName());
//		}
//		return courseList;
//	}
	
//	public ArrayList getStudentEnrolled() {
//		for (Student s: studentEnrolled) {
//			studentList.add(s.getName());
//		}
//		return studentList;
//	}
	
	public Double getDepartmentAverageGPA() {
		Double total = 0.0;
		for (Course c : courseOffered) {
			total += c.getAverageGrade();
		}
		averageGPA = total/courseOffered.size();
		return averageGPA;
	}
	
	public void compareCourseGPA() {
		Double grade = 0.0;
		for (Course c : courseOffered) {
			grade = c.getAverageGrade();
			if (grade > averageGPA) {
				highGPACourse.add(c);
			}
			else if (grade < averageGPA) {
				lowGPACourse.add(c);
			}
		} 
	}
	
	public ArrayList getHighGPACourse() {
		return highGPACourse;
	}
	
	public ArrayList getLowGPACourse() {
		return lowGPACourse;
	}
	
}
