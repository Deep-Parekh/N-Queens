import java.util.Random;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class SimulatedAnnealing {

	public Board solve(Board current) {
		long start = System.nanoTime();
		int time = 0;
		while(true) {
			//System.out.println(current);
			if(current.isGoal())
				break;
			double temperature = schedule(time);
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
		long elapsedTime = System.nanoTime() - start;
		System.out.println("It took " + elapsedTime + " nanoseconds with " + time + " iterations");
		System.out.println(current);
		return current;
	}

	// TODO A decay function
	public double schedule(int t) {
		return 1-(Math.log(t)/Math.log(10000));
	}
}
