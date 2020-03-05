import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author dparekh
 *
 */
public class Board {

	private byte[] state;
	private int LENGTH;
	private int attackingQueenPairs;	
	private boolean attackingPairsSet;
	
	/*
	 * Initializes a new random board
	 */
	public Board(int N) {
		this.LENGTH = N;
		this.state = new byte[LENGTH];
		this.attackingPairsSet = false;
		this.randomState();
		this.setAttackingQueenPairs();
	}
	
	/*
	 * Copies the board that was passed as a parameter
	 */
	public Board(Board b) {
		this.LENGTH = b.getLength();
		this.state = b.getState();
		this.attackingPairsSet = false;
		this.setAttackingQueenPairs();
	}

	public int getAttackingQueenPairs() {
		if(!this.attackingPairsSet)
			this.setAttackingQueenPairs();
		return this.attackingQueenPairs;
	}
	
	public int getLength() {
		return this.LENGTH;
	}
	
	public byte[] getState(){
		return this.state;
	}
	
	public boolean isGoal() {
		return this.attackingQueenPairs == 0;
	}
	/*
	 * Places N queens randomly on this board
	 */
	public void randomState() {
		Random random = new Random();
		for(int i = 0; i < this.LENGTH; ++i) {
			this.state[i] = (byte) random.nextInt(this.LENGTH);
		}
	}
	
	// Returns a random successor of the current state
	public Board getSuccessor(){
		Random random = new Random();
		Board successor = new Board(this);
		int column = random.nextInt(this.LENGTH);
		while(true) {
			successor.getState()[column] = (byte) random.nextInt(this.LENGTH);
			if(!this.equals(successor))
				return successor;
		}
	}
	
	public void setAttackingQueenPairs() {
		if(this.attackingPairsSet)
			return;
		this.attackingQueenPairs = 0;
		for(int i = 0; i < this.LENGTH-1; ++i) {
			for(int j = i + 1; j < this.LENGTH; ++j) {
				int difference = Math.abs(this.state[i]-this.state[j]);
				if(difference == 0 || j-i == difference)
					++this.attackingQueenPairs;
			}
		}
		this.attackingPairsSet = true;
	}
	
	// TODO
	@Override
	public String toString() {
		char[][] board = new char[this.LENGTH][this.LENGTH];
		for(char[] c: board)
			Arrays.fill(c, ' ');
//		for(Queen q : this.queens) 
//			board[q.getY()][q.getX()] = 'Q';
		String rtn = "";
		for(char[] c: board) 
			rtn += Arrays.toString(c);
		return rtn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().equals(this.getClass()))
			return false;
		Board b = (Board) obj;
		return Arrays.equals(this.state, b.getState());
	}
}
