import java.util.Comparator;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class BoardComparator implements Comparator<Board> {

	@Override
	public int compare(Board o1, Board o2) {
		// TODO Auto-generated method stub
		return o1.getAttackingQueenPairs()-o2.getAttackingQueenPairs();
	}

}
