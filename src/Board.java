import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author dparekh
 *
 */
public class Board {

	//private int[][] board;
	private List<Queen> queens;
	private int LENGTH;
	private int attackingQueenPairs;	
	private boolean attackingPairsSet;
	
	public Board(int N) {
		this.LENGTH = N;
		//this.board = new int[LENGTH][LENGTH];
		this.queens = new ArrayList<Queen>(LENGTH);
		this.attackingPairsSet = false;
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
			int x = random.nextInt(this.LENGTH);
			int y = random.nextInt(this.LENGTH);
			Queen queen = new Queen(x, y);
			if(this.queens.contains(queen)) {
				--i;
				continue;
			}
			queens.add(queen);
		}
	}
	
	// TODO
	public List<Board> getSuccessors(){
		return null;
	}
	
	public void setAttackingQueenPairs() {
		if(this.attackingPairsSet)
			return;
		this.attackingQueenPairs = 0;
		for(int i = 0; i < this.LENGTH; ++i) {
			Queen q1 = this.queens.get(i);
			int x1 = q1.getX();
			int y1 = q1.getY();
			for(int j = 0; j < this.LENGTH; ++j) {
				if(i == j)
					continue;
				Queen q2 = this.queens.get(j);
				int x2 = q2.getX();
				int y2 = q2.getY();
				if(x1 == x2 || y1 == y2 || (x1-y1) == (x2-y2) || (x1+y1) == (x2+y2))
					this.attackingQueenPairs++;
			}
		}
		this.attackingQueenPairs /= 2;
		this.attackingPairsSet = true;
	}
	
	public int getAttackingQueenPairs() {
		if(this.attackingPairsSet)
			return this.attackingQueenPairs;
		this.setAttackingQueenPairs();
		return this.attackingQueenPairs;
	}
	
	public int getLength() {
		return this.LENGTH;
	}
	
	public List<Queen> getQueens(){
		return this.queens;
	}
	
	public void setQueens(List<Queen> queens, int bound) {
		for(int i = 0; i < bound; ++i) {
			this.queens.add(queens.get(i));
		}
	}
	
	public void setQueens(List<Queen> queens, int start, int end) {
		for(int i = start; i < end; ++i) {
			this.queens.add(queens.get(i));
		}
	}
	
	@Override
	public String toString() {
		char[][] board = new char[this.LENGTH][this.LENGTH];
		for(char[] c: board)
			Arrays.fill(c, ' ');
		for(Queen q : this.queens) 
			board[q.getY()][q.getX()] = 'Q';
		String rtn = "";
		for(char[] c: board) 
			rtn += Arrays.toString(c);
		return rtn;
	}
}
