import java.util.Random;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class SimulatedAnnealing extends Algorithm {

	@Override
	public Board solve(Board current) {
		int time = 0;
		while(true) {
			if(current.isGoal())
				return current;
			double temperature = schedule(time);
			if(temperature <= 0)
				break;
			Board next = current.getSuccessor();
			int difference = next.getAttackingQueenPairs()-current.getAttackingQueenPairs();
			if(difference > 0)
				current = next;
			else {
				Random random = new Random();
				int d =random.nextInt(10000) / 10000;
				if(d < Math.pow(Math.E, difference/temperature))
					current = next;
			}
			++time;
		}
		return current;
	}

	// TODO A decay function
	public double schedule(int t) {
		return 1-(0.0005 * t);
	}
}
