import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class GeneticAlgorithm{
	
	private int POPULATION_SIZE;
	private int GENERATIONS;
	private double MUTATION_RATE;
	private double FITTEST;
	private int BOARD_LENGTH;

	public GeneticAlgorithm(int popSize) {
		this.GENERATIONS = 100;
		this.MUTATION_RATE = 0.20;
		this.FITTEST = 0.25;
		this.BOARD_LENGTH = 25;
		this.POPULATION_SIZE = popSize;
	}
	
	public GeneticAlgorithm(int popSize, int generation, double mutation, double fitness, int boardLen) {
		this.GENERATIONS = generation;
		this.MUTATION_RATE = mutation;
		this.FITTEST = fitness;
		this.BOARD_LENGTH = boardLen;
		this.POPULATION_SIZE = popSize;
	}
	
	public void initializePopulation(List<Board> population) {
		for(int i = 0; i < this.POPULATION_SIZE; ++i)
			population.add(new Board(this.BOARD_LENGTH));
	}
	
	public Board solve() {
		long start = System.nanoTime();
		List<Board> population = new ArrayList<Board>(this.POPULATION_SIZE);
		initializePopulation(population);
		population.sort(new BoardComparator());
		//this.printPopulation(population);
		Board fittest = population.get(0);
		for(int i = 0; i < GENERATIONS; ++i) {
			//System.out.println("Fittest in population:\n");
			//System.out.println(fittest);
			if(fittest.isGoal())
				break;
			List<Board> newPopulation = new ArrayList<Board>(this.POPULATION_SIZE);
			for(int j = 0; j < POPULATION_SIZE;) {
				Board child = generateChild(population);
				if(!newPopulation.contains(child)) {
					newPopulation.add(child);
					++j;
				}
			}
			population = newPopulation;
			population.sort(new BoardComparator());
			//this.printPopulation(population);
			fittest = population.get(0);
		}
		long elapsedTime = System.nanoTime() - start;
		System.out.println("It took " + elapsedTime + " nanoseconds");
		//System.out.println(fittest);
		return fittest;
	}
	
	public Board generateChild(List<Board> population) {
		Board[] parents = getParents(population);
		Board p1 = parents[0];
		Board p2 = parents[1];
		Board child = crossover(p1, p2);
		return child;
	}

	/*
	 * Returns a random board from the population
	 */
	public Board[] getParents(List<Board> population) {
		Random random = new Random();
		Board[] parents = new Board[2];
		int index = random.nextInt((int)(this.POPULATION_SIZE*this.FITTEST));
		parents[0] = population.get(index);
		int newIndex = random.nextInt((int)(this.POPULATION_SIZE*this.FITTEST));
		while (true) {
			Board parent2 = population.get(newIndex);
			if(!parent2.equals(parents[0])) {
				parents[1] = parent2;
				break;
			}
			newIndex = random.nextInt((int)(this.POPULATION_SIZE*this.FITTEST));
		}
		return parents;
	}
	
	
	public Board crossover(Board p1, Board p2) {
		Random random = new Random();
		int crossoverPoint = random.nextInt(BOARD_LENGTH-2) + 1; 	// Int between 1 and n-1
		Board child = new Board();
		byte[] state = child.getState();
		byte[] parentState1 = p1.getState();
		byte[] parentState2 = p2.getState();
		for(int i = 0; i < BOARD_LENGTH; ++i) {
			if(i < crossoverPoint)
				state[i] = parentState1[i];
			else
				state[i] = parentState2[i];
		}
		child = mutate(child);
		child.setAttackingQueenPairs();
		return child;
	}
	
	/*
	 * Mutates a child based on a predefined mutation rate
	 */
	public Board mutate(Board child) {
		Random random = new Random();
		if(Math.random() < this.MUTATION_RATE) 
			return child.getSuccessor();
		return child;
	}
	
	public void printPopulation(List<Board> population) {
		System.out.println("\nPrinting population: \n");
		for(Board b: population)
			System.out.println(b);
	}

}
