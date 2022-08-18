import ecs100.UI;

public class BoardUnit {
	double x, y, size;
	int id;
	
	BoardUnit(double x, double y, double size){
    	this.x = x;
    	this.y = y;
    	this.size = size;
    }
	
	public void draw(){
		UI.drawRect(this.x, this.y, this.size,this.size);
	}
	
	public void setID(int i) {
		this.id = i;
	}
	
	public int getID() {
		return this.id;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public boolean include(double x, double y) {
		return(x >= this.x && x <= this.x+ this.size &&
				y >= this.y && y<= this.y + this.size);
	}
}
