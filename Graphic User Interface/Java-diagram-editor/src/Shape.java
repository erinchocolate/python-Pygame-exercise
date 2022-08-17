import java.awt.Color;


public interface Shape {
	public void draw();
	public void fill();
	public void setPos(double x, double y);
	public void setColor(Color color);
	public void setLabel(String label);
	public void setLabelColor(Color color);
	public void drawLabel();
	public boolean isWithin(double x, double y);
	public void resize(double x, double y);
}