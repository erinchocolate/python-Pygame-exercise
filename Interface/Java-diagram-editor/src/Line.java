import java.awt.Color;
import java.awt.geom.Line2D;

import ecs100.UI;

public class Line implements Shape{
	private double x1,y1,x2,y2,deltaX,deltaY,lineWidth;
	private Color color;
	private Color labelColor;
	private String label;
	public static final int DEFAULT_WIDTH = 5;
	
	
	public Line(double x1,double y1,double x2,double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.lineWidth = DEFAULT_WIDTH;
		this.label = "";
		this.labelColor = Color.black;
		calculate();
	}
	
	public String toString() {
		return "Line" + "\n" + this.x1 + "\n" + this.y1 + "\n" + 
	this.x2 + "\n" + this.y2 +"\n"+ this.color.getRed() + "\n" + this.color.getGreen()
	+ "\n" + this.color.getBlue() + "\n";
	}
	
	public void calculate() {
		this.deltaX = this.x2-this.x1;
		this.deltaY = this.y2-this.y1;
	}
	
	@Override
	public void draw() {
		UI.setLineWidth(DEFAULT_WIDTH);
		UI.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void fill() {
		UI.setLineWidth(DEFAULT_WIDTH);
		UI.setColor(color);
		UI.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void setPos(double x, double y) {
		this.x1 = x ;
		this.y1 = y ;
		this.x2 = x + this.deltaX;
		this.y2 = y + this.deltaY;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean isWithin(double x, double y) {
		double distance = Line2D.ptSegDist(x1, y1, 
                x2, y2,
                x, y);
		return (distance<10);
	}

	@Override
	public void resize(double x, double y) {
		this.x2 = x;
		this.y2 = y;
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
		UI.drawString(label, x1, y1);
	}

}
