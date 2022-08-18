import java.awt.Color;

import ecs100.UI;

public class Circle implements Shape{
	private double x1,y1,x2,y2,radius;
	private Color color;
	private Color labelColor;
	private String label;
	
	public Circle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.label = "";
		this.labelColor = Color.black;
		calculate();
	}
	
	public String toString() {
		return "Circle" + "\n" + this.x1 + "\n" + this.y1 + "\n" + 
	this.x2 + "\n" + this.y2 + "\n" + this.color.getRed() + "\n" + this.color.getGreen()
	+ "\n" + this.color.getBlue() + "\n";
	}
	
	public void calculate() {
		this.radius = (Math.abs(this.x2-this.x1))/2;
	}
	
	@Override
	public void draw() {
		UI.drawOval(x1, y1, radius*2, radius*2);
	}

	@Override
	public void fill() {
		UI.setColor(color);
		UI.fillOval(x1, y1, radius*2, radius*2);
	}

	@Override
	public void setPos(double x, double y) {
		this.x1 = x - this.radius;
		this.y1 = y - this.radius;
		this.x2 = x + this.radius;
		this.y2 = y + this.radius;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean isWithin(double x, double y) {
		double x0 = this.x1 + this.radius;
		double y0 = this.y1 + this.radius;
		double a = Math.abs(x0-x);
		double b = Math.abs(y0-y);
		double c = Math.sqrt(a*a + b*b);
		return c <= radius;
	}

	@Override
	public void resize(double x, double y) {
		this.x2 = x;
		this.y2 = y;
		calculate();	
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}


	@Override
	public void setLabelColor(Color color) {
		this.labelColor = color;
	}

	@Override
	public void drawLabel() {
		UI.setColor(this.labelColor);
		UI.drawString(label, x1+this.radius/2, y1+this.radius/2);
	}

}
