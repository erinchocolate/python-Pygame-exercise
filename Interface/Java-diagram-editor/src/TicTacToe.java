import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import ecs100.UI;

public class TicTacToe {
	public static final int LINE_WIDTH = 10;
	public static final double LEFT = 50.0;
	public static final double TOP = 50.0;
	public static final int UNIT_SIZE = 100;
	public static final double DISTANCE = 10.0;
	public static final double RADIUS = 80;
	private ArrayList<BoardUnit> units = new ArrayList<BoardUnit>();
	private ArrayList<Integer> movePlaced = new ArrayList<Integer>();
	private BoardUnit selectedUnit;	
	private String mode;
	char [][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
	char winner = ' ';
	
	TicTacToe(){
		initGameBoard();
		UI.setMouseListener(this::doMouse);
	}
	
	private void initGameBoard() {
		UI.setColor(Color.red);
		double y = TOP;
		int id = 1;
		for (int i = 0; i < 3; i++) {
			double x = LEFT;
        	y += UNIT_SIZE;
        	for (int j = 0; j < 3; j ++) {
        		x += UNIT_SIZE;    		
        		BoardUnit unit = new BoardUnit(x, y, UNIT_SIZE);
        		unit.setID(id);
        		units.add(unit);
        		unit.draw();
        		id++;
        	}
		}
	}
	
	private void checkClick(double x, double y) {
		if(x >= LEFT && x < LEFT + UNIT_SIZE*3 && y >= TOP && y < TOP + UNIT_SIZE*3) {
			this.mode = "gameOn";
		}
	}
	
	public void findUnit(double x, double y) {
		for(BoardUnit u:units) {
			if(u.include(x, y)) {
				this.selectedUnit = u;
				}
			}
		}
	
	public void findUnit(int id) {
		for(BoardUnit u:units) {
			if(u.getID() == id) {
				this.selectedUnit = u;
				}
			}
		}
	
	public boolean isMoveValid(int id) {
		return(!movePlaced.contains(id));
	}
	
	private void playerMove() {
		while(true) {
			if(isMoveValid(this.selectedUnit.getID())) {
			UI.println("Player chose " + this.selectedUnit.getID());
			movePlaced.add(this.selectedUnit.getID());
			double x = this.selectedUnit.getX() + DISTANCE;
			double y = this.selectedUnit.getY() + DISTANCE;
			placeMove(board, this.selectedUnit.getID(), 'O', x, y);
			if(movePlaced.size()<9 & this.mode.equals("gameOn")) {
				computerMove();
			}
			break;
			}	
		}
	}
	
	private void computerMove() {
		int randNum;
		while(true) {
			Random rand = new Random();
			randNum = rand.nextInt(9)+1;
			findUnit(randNum);
			if (isMoveValid(this.selectedUnit.getID())) {
				UI.println("Computer chose " + this.selectedUnit.getID());
				movePlaced.add(this.selectedUnit.getID());
				double x = this.selectedUnit.getX() + DISTANCE;
				double y = this.selectedUnit.getY() + DISTANCE;
				placeMove(board, this.selectedUnit.getID(), 'X', x, y);
				break;
			}
		}
	}
	
	private void getWinner() {
		for(int i = 0; i <board.length; i ++) {
			if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0] != ' ') {
				winner = board[i][0];
			}
		}
		
		for(int i = 0; i <board.length; i ++) {
			if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i] != ' ') {
				winner = board[0][i];
			}
		}
		
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0] != ' ') {
			winner = board[0][0];
		}
		
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2] != ' ') {
			winner = board[0][2];
		}
		
	}
	
	private void checkGameOver() {
		getWinner();
		if(this.winner != ' ') {
			if (this.winner == 'X') {
				UI.setFontSize(40);
				UI.setColor(Color.BLUE);
				UI.drawString("Winner is computer!", LEFT + UNIT_SIZE, TOP+UNIT_SIZE*5);
				UI.println("Winner is computer!");
			}
			else {
				UI.setFontSize(40);
				UI.setColor(Color.GREEN);
				UI.drawString("Winner is player!", LEFT + UNIT_SIZE, TOP+UNIT_SIZE*5);
				UI.println("Winner is player!");
			}	
			this.mode = "gameOver";
		}
		else if(movePlaced.size()==9) {
			UI.println("This game is a tie!");
			UI.setFontSize(40);
			UI.setColor(Color.RED);
			UI.drawString("This game is a tie!", LEFT + UNIT_SIZE, TOP+UNIT_SIZE*5);
			this.mode = "gameOver";
		}
	}
	
	private void placeMove(char[][] board, Integer position, char symbol, double x, double y) {
		switch(position) {	
		case 1:
			board[0][0] = symbol;
			if(symbol == 'O') {
				GameCircle circle1 = new GameCircle(x, y, RADIUS);
				circle1.draw();
			}
			else {
				GameCircle circle1 = new GameCircle(x, y, RADIUS);
				circle1.fill();
			}
			
			break;
		case 2:
			board[0][1] = symbol;
			if(symbol == 'O') {
				GameCircle circle2 = new GameCircle(x, y, RADIUS);
				circle2.draw();
			}
			else {
				GameCircle circle2 = new GameCircle(x, y, RADIUS);
				circle2.fill();
			}
			break;
		case 3:
			board[0][2] = symbol;
			if(symbol == 'O') {
				GameCircle circle3 = new GameCircle(x, y, RADIUS);
				circle3.draw();
			}
			else {
				GameCircle circle3 = new GameCircle(x, y, RADIUS);
				circle3.fill();
			}
			break;
		case 4:
			board[1][0] = symbol;
			if(symbol == 'O') {
				GameCircle circle4 = new GameCircle(x, y, RADIUS);
				circle4.draw();
			}
			else {
				GameCircle circle4 = new GameCircle(x, y, RADIUS);
				circle4.fill();
			}
			break;
		case 5:
			board[1][1] = symbol;
			if(symbol == 'O') {
				GameCircle circle5 = new GameCircle(x, y, RADIUS);
				circle5.draw();
			}
			else {
				GameCircle circle5 = new GameCircle(x, y, RADIUS);
				circle5.fill();
			}
			break;
		case 6:
			board[1][2] = symbol;
			if(symbol == 'O') {
				GameCircle circle6 = new GameCircle(x, y, RADIUS);
				circle6.draw();
			}
			else {
				GameCircle circle6 = new GameCircle(x, y, RADIUS);
				circle6.fill();
			}
			break;
		case 7:
			board[2][0] = symbol;
			if(symbol == 'O') {
				GameCircle circle7 = new GameCircle(x, y, RADIUS);
				circle7.draw();
			}
			else {
				GameCircle circle7 = new GameCircle(x, y, RADIUS);
				circle7.fill();
			}
			break;
		case 8:
			board[2][1] = symbol;
			if(symbol == 'O') {
				GameCircle circle8 = new GameCircle(x, y, RADIUS);
				circle8.draw();
			}
			else {
				GameCircle circle8 = new GameCircle(x, y, RADIUS);
				circle8.fill();
			}
			break;
		case 9:
			board[2][2] = symbol;
			if(symbol == 'O') {
				GameCircle circle9 = new GameCircle(x, y, RADIUS);
				circle9.draw();
			}
			else {
				GameCircle circle9 = new GameCircle(x, y, RADIUS);
				circle9.fill();
			}
			break;	
		}
	}

	public void doMouse(String action,double x, double y) {
		if(this.mode == null) {
			checkClick(x,y);
		}
		
		else if (this.mode.equals("gameOn")&& action.equals("pressed")) {
			findUnit(x, y);
			playerMove();
		}	
		
		else if ((this.mode!= null) && action.equals("released")) {
			checkGameOver();
		}
	}
}
