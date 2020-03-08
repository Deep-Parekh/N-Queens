import java.util.Random;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class SimulatedAnnealing {
	public long iterations;

	public Board solve(Board current, Schedule schedule) {
		int time = 0;
		while(true) {
			//System.out.println(current);
			if(current.isGoal())
				break;
			double temperature = schedule.schedule(time);
			if(temperature <= 0)
				break;
			Board next = current.getSuccessor();
			int difference = current.getAttackingQueenPairs()-next.getAttackingQueenPairs();
			if(difference > 0)
				current = next;
			else {
				Random random = new Random();
				double d = Math.random();
				if(d < Math.pow(Math.E, difference/temperature))
					current = next;
			}
			++time;
		}
		iterations += time;
		//System.out.println(current);
		return current;
	}
}
