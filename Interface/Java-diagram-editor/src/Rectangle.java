import java.awt.Color;

import ecs100.UI;

public class Rectangle implements Shape{
	private double x1,y1,x2,y2,width,height;
	private Color color;
	private Color labelColor;
	private String label;
	
	Rectangle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.label = "";
		this.labelColor = Color.black;
		calculate();
	}
	
	public String toString() {
		return "Rectangle" + "\n" + this.x1 + "\n" + this.y1 + "\n" + 
	this.x2 + "\n" + this.y2 + "\n" + this.color.getRed() + "\n" + this.color.getGreen()
	+ "\n" + this.color.getBlue() + "\n";
	}
	
	public void calculate() {
		this.width = Math.abs(this.x2-this.x1);
		this.height = Math.abs(this.y2-this.y1);
	}
	
	@Override
	public void draw() {
		UI.drawRect(this.x1, this.y1, this.width, this.height);
	}
	
	@Override
	public void fill() {
		UI.setColor(color);
		UI.fillRect(this.x1, this.y1, this.width,this.height);
	}

	@Override
	public void setPos(double x, double y) {
		this.x1 = x - this.width/2;
		this.y1 = y - this.height/2;
		this.x2 = x + this.width/2;
		this.y2 = y + this.height/2;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean isWithin(double x, double y) {
		return(x >= this.x1 && x <= this.x1+ this.width &&
				y >= this.y1 && y<= this.y1 + this.height);
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
		UI.drawString(label, x1 + this.width/4, y1 + this.height/2);
	}
	

}
