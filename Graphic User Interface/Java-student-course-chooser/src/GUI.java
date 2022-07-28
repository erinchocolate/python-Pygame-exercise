import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ecs100.UI;

public class GUI {
	private Department department;
	
	private Integer courseNum;
	
	private ArrayList<String> defaultCourseList = new ArrayList<String>();
	private ArrayList<String> defaultNameList = new ArrayList<String>();
	
	private ArrayList<String> selectedCourseList = new ArrayList<String>();
	private ArrayList<String> selectedStudentList = new ArrayList<String>();
	
	private ArrayList<String> courseNameList = new ArrayList<String>();
	private ArrayList<String> studentNameList = new ArrayList<String>();
	
	private ArrayList<Course> courseList = new ArrayList<Course>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	public GUI() {
		UI.initialise();
		UI.addButton("Load Data", this::loadData);
		UI.addButton("Add Student", this::addStudent);
		UI.addButton("Add Course", this::addCourse);
		UI.addButton("Show Course", this::displayCourse);
		UI.addButton("Show Student", this::displayStudent);
		UI.addButton("Match", this::addStudentToCourse);
		UI.addButton("Student info", this::getStudentInfo);
		UI.addButton("Course info", this::getCourseInfo);
		UI.addButton("Quit", UI::quit);
	}
	
	public void loadData() {
		loadCourseData();
		loadNameData();
		UI.println("Creating a department...");
		department = new Department("Engineering and Computer Science");
		UI.println("School of " + department.getName() + " is generated!");
	}
	
	public void loadCourseData() {
		try {
			Scanner scan = new Scanner(new File("course.txt"));
			while (scan.hasNextLine()) {
				defaultCourseList.add(scan.nextLine());
			}
		} catch (IOException e) {
			UI.println("Error: " + e);
		}
	}
	
	public void loadNameData() {
		try {
			Scanner scan = new Scanner(new File("name.txt"));
			while (scan.hasNextLine()) {
				defaultNameList.add(scan.nextLine());
			}
		} catch (IOException e) {
			UI.println("Error: " + e);
		}
	}
	
	public void addCourse() {
		Integer courseNum = UI.askInt("How many courses do you want to add?");	
		createCourse(courseNum);
		UI.println("Generating courses...Done");
	}
	
	public void createCourse(Integer num) {
		for(int i = 0; i < num; i ++) {
			//Create course
			String courseName = generateRandomCourseName();
			Course newCourse = new Course(courseName);
			newCourse.setPoint();
			courseList.add(newCourse);
			
			//Create lecturer 
			Lecturer newLecturer = new Lecturer(generateRandomName());
			
			//Add course to the lecturer
			newLecturer.addCourseTeached(newCourse);
			
			//Add lecturer to the course
			newCourse.addLecturer(newLecturer);
			//Add course to the department
			department.addCourseOffered(newCourse);
			
		}
	}
	
	public void addStudent() {
		Integer studentNum = UI.askInt("How many students do you want to add?");
		createStudent(studentNum);
		UI.println("Generating students...Done");
	}
	
	public void createStudent(Integer num) {
		for(int i = 0; i < num; i ++) {
			Student newStudent = new Student(generateRandomName());
			newStudent.setTutionForPoint();
			studentList.add(newStudent);
			department.addStudentEnrolled(newStudent);
		}
	}
	
	public void addStudentToCourse() {	
		for(int i = 0; i < studentList.size(); i ++) {	
			generateRandomCourseNum();
			for(int j = 0; j < courseNum; j ++) {
				Integer grade = 0;
				Course course = getRandomCourse();
				//Add course into student's list
				studentList.get(i).addCourseEnrolled(course);
				//Add student into course's list
				course.addEnrolledStudent(studentList.get(i));
				//Get student grade for this course
				studentList.get(i).generateRandomGrade();
				grade = studentList.get(i).getGrade();
				//Add student grade into course grade list
				course.addGrade(grade);	
			}
		}
	}
	
	public void getCourseInfo() {
		String courseName = UI.askString("What course do you want to search?");
		if (selectedCourseList.contains(courseName)) {
			int courseIndex = selectedCourseList.indexOf(courseName);
			Course course = courseList.get(courseIndex);
			course.setCourseIncome();
			course.setCourseProfit();
			UI.println("Course name: " + course.getName());
			UI.println("Course lecturer: " + course.getLecturerList());
			UI.println("Course can take: " + course.getLimit() + " students");
			UI.println("Course point: " + course.getPoint());
			UI.println("Course enroll list: " + course.getEnrolledStudent());
			UI.println("Course average grade: " + course.getAverageGrade());
			UI.println("Course lecturer cost: " + course.getLecturerCost());
			UI.println("Course room cost: " + course.getRoomCost());
			UI.println("Course income: " + course.getCourseIncome());
			UI.println("Course profit: " + course.getCourseProfit());
		}
		else {
			UI.println("The course you entered doesn't exsit in the course list!");
		}
		
	}
	
	public void getStudentInfo() {
		String studentName = UI.askString("What student do you want to search?");
		if (selectedStudentList.contains(studentName)) {
			int studentIndex = selectedStudentList.indexOf(studentName);
			Student student = studentList.get(studentIndex);
			student.setTotalTution();
			UI.println("Student name: " + student.getName());
			UI.println(student.getName() + " pays " + student.getTutionForPoint() + " per point for a course");
			UI.println(student.getName() + " pays " + student.getTotalTution() + " for all courses");
			UI.println(student.getName() + " can take " + student.getLimit() + " courses");
			if (student.getCourseEnrolled().size() <= 1) {
				UI.println(student.getName() + " acturally takes " + student.getCourseEnrolled().size() + " course");
			}
			else {
				UI.println(student.getName() + " acturally takes " + student.getCourseEnrolled().size() + " courses");
			}
			UI.println(student.getName() + "'s course list: " + student.getCourseEnrolled());
			UI.println(student.getName() + "'s grade list: " + student.getGradeList());
			UI.println(student.getName() + "'s average grade is " + student.getAverageGrade());
		}
		else {
			UI.println("The student you entered doesn't exsit in the student list!");
		}
		
	}
		
	public Course getRandomCourse() {
		Random rand = new Random();
		Integer courseIndex = rand.nextInt(courseList.size());
		return courseList.get(courseIndex);
	}
	
	public String generateRandomName() {
		Random rand = new Random();
		String newName = "";
		Integer nameIndex = 0;
		nameIndex = rand.nextInt(defaultNameList.size());
		newName = defaultNameList.get(nameIndex);
		//Avoid duplicate name
		while (studentNameList.contains(newName)) {
			rand = new Random();
			newName = defaultNameList.get(rand.nextInt(defaultNameList.size()));
		}
		studentNameList.add(newName);
		return newName;
	}
	
	public String generateRandomCourseName() {
		Random rand = new Random();
		String courseName = "";
		Integer courseIndex = 0;
		courseIndex = rand.nextInt(defaultCourseList.size());
		courseName = defaultCourseList.get(courseIndex);
		//Avoid duplicate name
		while (courseNameList.contains(courseName)) {
			rand = new Random();
			courseName = defaultCourseList.get(rand.nextInt(defaultCourseList.size()));
		}
		courseNameList.add(courseName);
		return courseName;
	}
	
	public void generateRandomCourseNum() {
		Random rand = new Random();
		//Generate a random number between 0 and the max number of course the student can take
		courseNum = rand.nextInt(Student.getStudentLimit());
	}
	
	public void displayCourse() {
		UI.clearText();
		selectedCourseList = department.getCourseOffered();
		UI.println("Here is the course list:");
		for (String s: selectedCourseList) {
			UI.println(s);
		}
	}
	
	public void displayStudent() {
		UI.clearText();
		selectedStudentList = department.getStudentEnrolled();
		UI.println("Here is the student list:");
		for (String s: selectedStudentList) {
			UI.println(s);
		}
	}
	
}
