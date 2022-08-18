import java.awt.Color;

import ecs100.UI;

public class GameCircle {
	double x, y, size;
	
	GameCircle(double x, double y, double size){
    	this.x = x;
    	this.y = y;
    	this.size = size;
    }
	
	public void draw(){
		UI.setColor(Color.GREEN);
		UI.fillOval(this.x, this.y, this.size, this.size);
	}
	
	public void fill(){
		UI.setColor(Color.BLUE);
		UI.fillOval(this.x, this.y, this.size, this.size);
	}
}
