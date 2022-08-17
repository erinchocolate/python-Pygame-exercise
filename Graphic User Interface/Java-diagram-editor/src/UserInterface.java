import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JColorChooser;

import ecs100.UI;
import ecs100.UIFileChooser;

public class UserInterface {
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<Double> cordinate = new ArrayList<>();
	private String mode;
	private Shape selectedShape;	
	
	public UserInterface() {
		this.mode = null;
		UI.initialise();
		UI.setMouseListener(this::doMouse);
		UI.addButton("Add Rectangle", this::drawRect);
		UI.addButton("Add Circle", this::drawCircle);
		UI.addButton("Add Line", this::drawLine);
		UI.addButton("Move", this::moveMode);
		UI.addButton("Resize", this::resizeMode);
		UI.addButton("Change Color", this::changeColorMode);
		UI.addButton("Add Label", this::drawLabelMode);
		UI.addButton("Move Forward", this::forwardMode);
		UI.addButton("Remove", this::removeMode);
		UI.addButton("Save File", this::saveFile);
		UI.addButton("Open File", this::openFile);
		UI.addButton("Clear", this::clear);
		UI.addButton("Tic Tac Toe", this::TicTacToe);
	}
	
	public void drawRect(){
		this.mode = "drawRec";
	}
	
	public void drawCircle(){
		this.mode = "drawCircle";
	}
	
	public void drawLine(){
		this.mode = "drawLine";
	}
	
	public void drawShapes() {
		for(Shape s:shapes) {
			s.fill();
		}
	}
	
	public void clear() {
		UI.clearGraphics();
		shapes.clear();
	}
	
	public boolean shapeExsits() {
		return shapes.size()!=0;
	}
	
	public void findShape(double x, double y) {
		for(Shape s:shapes) {
			if(s.isWithin(x, y)) {
				this.selectedShape = s;
				}
			}
		}
	
	public void moveMode() {	
		this.selectedShape = null;
		if(shapeExsits()) {
			UI.println("You are in move mode!");
			UI.println("Click on the shape to select it.");
			this.mode = "move";
		}
		else {
			UI.println("There is no shape to move!");
		}
	}
	
	public void moveShape(double x, double y) {
		this.selectedShape.setPos(x, y);
		UI.clearGraphics();
		for (Shape s:shapes) {
			s.fill();
			s.drawLabel();
		}
	}
	
	public void resizeMode() {
		this.selectedShape = null;
		if(this.shapeExsits()) {
			UI.println("You are in modify mode!");
			UI.println("Click on the shape to select it.");
			this.mode = "resize";
		}
		else {
			UI.println("There is no shape to modify!");
		}
	}
	
	public void resizeShape(double x, double y) {
		this.selectedShape.resize(x,y);
		UI.clearGraphics();
		for (Shape s:shapes) {
			s.fill();
			s.drawLabel();
		}
	}
	
	public void removeMode() {
		this.selectedShape = null;
		if(this.shapeExsits()) {
			UI.println("You are in remove mode!");
			UI.println("Click on the shape to select it.");
			this.mode = "remove";
		}
		else {
			UI.println("There is no shape to remove!");
		}
	}
	
	public void removeShape() {
		shapes.remove(this.selectedShape);
		UI.clearGraphics();
		for (Shape s:shapes) {
			s.fill();
			s.drawLabel();
		}
	}
	
	public void changeColorMode() {
		this.selectedShape = null;
		if(shapeExsits()) {
			this.mode = "changeColor";
		}
		else {
			UI.println("There is no shape to change color!");
		}
	}
	
	public void changeColor() {
		Color color = JColorChooser.showDialog(null, "choose a color", Color.RED);
		this.selectedShape.setColor(color);
		UI.clearGraphics();
		for (Shape s:shapes) {
			s.fill();
			s.drawLabel();
		}
	}
	
	public void drawLabelMode(){
		this.selectedShape = null;
		if(shapeExsits()) {
			this.mode = "drawLabel";
		}
		else {
			UI.println("There is no shape to add a label!");
		}
	}
	
	public void addLabel() {
		String input = UI.askString("Please enter your text: ");
		this.selectedShape.setLabel(input);
		Color color = JColorChooser.showDialog(null, "choose a color", Color.RED);
		this.selectedShape.setLabelColor(color);
		this.selectedShape.fill();
		this.selectedShape.drawLabel();
	}
	
	public void forwardMode(){
		this.selectedShape = null;
		if(shapeExsits()) {
			this.mode = "drawForward";
		}
		else {
			UI.println("There is no shape to move forward!");
		}
	}
	
	public void drawForward(){
		this.selectedShape.fill();
	}
	
	public void addRect() {
		double x1 = cordinate.get(0);
		double y1 = cordinate.get(1);
		double x2 = cordinate.get(2);
		double y2 = cordinate.get(3);
		Rectangle rec = new Rectangle(x1, y1, x2, y2);
		rec.draw();
		Color color = JColorChooser.showDialog(null, "choose a color", Color.RED);
		rec.setColor(color);
		rec.fill();
		shapes.add(rec);
		cordinate.clear();
	}
	
	public void addCircle() {
		double x1 = cordinate.get(0);
		double y1 = cordinate.get(1);
		double x2 = cordinate.get(2);
		double y2 = cordinate.get(3);
		Circle circle = new Circle(x1, y1, x2, y2);
		circle.draw();
		Color color = JColorChooser.showDialog(null, "choose a color", Color.RED);
		circle.setColor(color);
		circle.fill();
		shapes.add(circle);
		cordinate.clear();
	}
	
	public void addLine() {
		double x1 = cordinate.get(0);
		double y1 = cordinate.get(1);
		double x2 = cordinate.get(2);
		double y2 = cordinate.get(3);
		Line line = new Line(x1, y1, x2, y2);
		line.draw();
		Color color = JColorChooser.showDialog(null, "choose a color", Color.RED);
		line.setColor(color);
		line.fill();
		shapes.add(line);
		cordinate.clear();
	}
	
	public void saveFile() {
		File file = new File(UIFileChooser.save());
		try {
			PrintStream output = new PrintStream(file);
			for(Shape s: shapes) {
				output.print(s.toString());
			}
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void openFile() {
		UI.clearGraphics();
		shapes.clear();
		File file = new File(UIFileChooser.open());
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String shapeType = scan.nextLine().trim();
				double x1 = Double.parseDouble(scan.nextLine().trim());
				double y1 = Double.parseDouble(scan.nextLine().trim());
				double x2 = Double.parseDouble(scan.nextLine().trim());
				double y2 = Double.parseDouble(scan.nextLine().trim());
				int colorRed = Integer.parseInt(scan.nextLine().trim());
				int colorGreen = Integer.parseInt(scan.nextLine().trim());
				int colorBlue = Integer.parseInt(scan.nextLine().trim());
				Color color = new Color(colorRed, colorGreen, colorBlue);
				if (shapeType.equals("Circle")) {
					Circle circle = new Circle(x1, y1, x2, y2);
					circle.setColor(color);
					shapes.add(circle);
				}
				else if(shapeType.equals("Rectangle")) {
					Rectangle rect = new Rectangle(x1, y1, x2, y2);
					rect.setColor(color);
					shapes.add(rect);
				}
				else if(shapeType.equals("Line")) {
					Line line = new Line(x1, y1, x2, y2);
					line.setColor(color);
					shapes.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		drawShapes();
	}
	
	public void TicTacToe() {
		UI.clearPanes();
		TicTacToe game = new TicTacToe();
	}
	
	
	public void doMouse(String action,double x, double y) {
		if(this.mode == null) {
		}
		
		else if ((this.mode.equals("drawRec")||this.mode.equals("drawCircle")||this.mode.equals("drawLine")||this.mode.equals("drawText")) && action.equals("pressed")) {
			cordinate.add(x);
			cordinate.add(y);
		}
		
		else if (this.mode.equals("drawRec") && action.equals("released")) {
			cordinate.add(x);
			cordinate.add(y);
			addRect();
		}
		
		else if (this.mode.equals("drawCircle") && action.equals("released")) {
			cordinate.add(x);
			cordinate.add(y);
			addCircle();
		}
		
		else if (this.mode.equals("drawLine") && action.equals("released")) {
			cordinate.add(x);
			cordinate.add(y);
			addLine();
		}
		
		else if ((this.mode.equals("move") ||this.mode.equals("remove") || this.mode.equals("resize") || this.mode.equals("changeColor") || this.mode.equals("drawLabel") || this.mode.equals("drawForward")) && action.equals("pressed")) {
			findShape(x, y);
		}
		
		else if (this.mode.equals("move") && action.equals("released") && (this.selectedShape != null)) {
			moveShape(x, y);
		}
		
		else if (this.mode.equals("resize") && action.equals("released")&& (this.selectedShape != null)) {
			resizeShape(x, y);
		}	
		
		else if (this.mode.equals("remove") && action.equals("released")&& (this.selectedShape != null)) {
			removeShape();
		}
		
		else if (this.mode.equals("changeColor") && action.equals("released")&& (this.selectedShape != null)) {
			changeColor();
		}
		
		else if (this.mode.equals("drawLabel") && action.equals("released")&& (this.selectedShape != null)) {
			addLabel();
		}
		
		else if (this.mode.equals("drawForward") && action.equals("released")&& (this.selectedShape != null)) {
			drawForward();
		}
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}

}
