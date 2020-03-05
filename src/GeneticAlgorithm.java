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
	private int MUTATION_RATE;
	private int FITTEST;
	private int BOARD_LENGTH;

	public GeneticAlgorithm(int popSize) {
		this.GENERATIONS = 100;
		this.MUTATION_RATE = 20;
		this.FITTEST = 25;
		this.BOARD_LENGTH = 25;
		this.POPULATION_SIZE = popSize;
	}
	
	public GeneticAlgorithm(int popSize, int gen, int mut, int fit, int boardLen) {
		this.GENERATIONS = gen;
		this.MUTATION_RATE = mut;
		this.FITTEST = fit;
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
		Board fittest = population.get(0);
		for(int i = 0; i < GENERATIONS; ++i) {
			System.out.println(fittest);
			if(fittest.isGoal())
				break;
			List<Board> newPopulation = new ArrayList<Board>(this.POPULATION_SIZE);
			for(int j = 0; j < POPULATION_SIZE; ++j) {
				newPopulation.add(generateChild(population));
			}
			population = newPopulation;
			population.sort(new BoardComparator());
			fittest = population.get(0);
		}
		long elapsedTime = System.nanoTime() - start;
		System.out.println("It took " + elapsedTime + " nanoseconds");
		System.out.println(fittest);
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
		int index = random.nextInt(FITTEST);	//Selects parent from the fittest population
		parents[0] = population.get(index);
		int newIndex = random.nextInt(FITTEST);
		while (true) {
			if(newIndex != index) {
				parents[1] = population.get(newIndex);
				break;
			}
			newIndex = random.nextInt(FITTEST);
		}
		return parents;
	}
	
	
	public Board crossover(Board p1, Board p2) {
		Random random = new Random();
		int crossoverPoint = random.nextInt(BOARD_LENGTH-2) + 1; 	// Int between 1 and n-1
		Board child = new Board();
		for(int i = 0; i < BOARD_LENGTH; ++i) {
			if(i < crossoverPoint)
				child.getState()[i] = p1.getState()[i];
			else
				child.getState()[i] = p2.getState()[i];
		}
		mutate(child);
		child.setAttackingQueenPairs();
		return child;
	}
	
	/*
	 * Mutates a child based on a predefined mutation rate
	 */
	public void mutate(Board child) {
		Random random = new Random();
		int m = random.nextInt(100);
		if(m < this.MUTATION_RATE) 
			child = child.getSuccessor();
	}

}
