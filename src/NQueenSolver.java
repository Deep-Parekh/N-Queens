/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class NQueenSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board(25);
//		System.out.println("Board is: ");
//		System.out.println(board);
//		SimulatedAnnealing sa = new SimulatedAnnealing();
//		int success = 0;
//		for(int i = 0; i < 1000; ++i) {
//			Board res = sa.solve(board);
//			if(res.isGoal())
//				++success;
//			board = new Board(25);
//		}
//		System.out.println("Success percentage: " + (double)success/1000);
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 1000, .25, .20, 25);
		int success = 0;
		for(int i = 0; i < 1000; ++i) {
			Board res = ga.solve();
			if(res.isGoal())
				++success;
		}
		System.out.println("Success percentage: " + (double)success/1000);
	}

}

/*
 * Simulated Annealing Results
 *
 * log 100000 success rate is 100%
 * log 10000 success rate is 73.9%
 * log 1000 success rate is 1.7%
 */

/*
 * Genetic Algorithm Results 
 * 
 * 0.957 success rate new GeneticAlgorithm(100, 1000, .25, .20, 25);
 * 0.723 success rate new GeneticAlgorithm(50, 500, .25, .25, 25);
 */