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
		SimulatedAnnealing sa = new SimulatedAnnealing();
		Schedule sched = t -> { return 1-(Math.log(t)/Math.log(50000)); };
		for(int i = 0; i < 3; ++i)
			report(sa, sched);
		GeneticAlgorithm ga;
		ga = new GeneticAlgorithm(100, 1000, .50, .1, 25);
		for(int i = 0; i < 3; ++i)
			report(ga);	
	}

	
	public static void report(GeneticAlgorithm ga) {
		int success = 0;
		System.out.println("Solving using Genetic Algorithm with parameters: ");
		System.out.print("Generations: " + ga.GENERATIONS);
		System.out.print(", Population Size: " + ga.POPULATION_SIZE);
		System.out.print(", Mutation Rate: " + ga.MUTATION_RATE);
		System.out.println(", Fitness Threshold: " + ga.FITTEST);
		long start = System.nanoTime();
		Board res = ga.solve();
		if(res.isGoal())
			++success;
		long end = System.nanoTime();
		System.out.println("Total iterations: " + ga.iterations);
		System.out.println("Runtime: " + (double)(end-start)/(1000) + " milliseconds");
		System.out.println(res);
		System.out.println();
	}
	
	public static void report(SimulatedAnnealing sa, Schedule schedule) {
		Board board = new Board(25);
		System.out.println("Solving using Simulated Annealing");
		System.out.println("Schedule function: 1 - log(t)/(log50000)");
		int success = 0;
		long start = System.nanoTime();
		Board res = sa.solve(board, schedule);
		if(res.isGoal())
			++success;
		long end = System.nanoTime();
		System.out.println("Total iterations: " + sa.iterations);
		System.out.println("Runtime: " + (double)(end-start)/(1000) + " milliseconds");
		System.out.println(res);
		System.out.println();
	}
}

/*
 * Simulated Annealing Results
 *
 * log 100000 success rate is 100%
 * log 50000 success rate is 99.8%
 * log 10000 success rate is 73.9%
 * log 1000 success rate is 1.7%
 */

/*
 * Genetic Algorithm Results 
 * 
 * 0.957 success rate new GeneticAlgorithm(100, 1000, .25, .20, 25);
 * 0.723 success rate new GeneticAlgorithm(50, 500, .25, .25, 25);
 */