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
public class GeneticAlgorithm extends Algorithm {
	private List<Board> population;
	private int popSize;
	private int GENERATIONS;
	private int MUTATION_RATE;
	private int FITTEST;

	public GeneticAlgorithm(int popSize) {
		this.GENERATIONS = 100;
		this.MUTATION_RATE = 30;
		this.popSize = popSize;
		this.population = new ArrayList<Board>(this.popSize);
	}
	
	public GeneticAlgorithm(int popSize, int gen, int mut) {
		this.GENERATIONS = gen;
		this.MUTATION_RATE = mut;
		this.popSize = popSize;
		this.population = new ArrayList<Board>(this.popSize);
		this.population.sort(new BoardComparator());
	}
	
	@Override
	public Board solve(Board board) {
		// TODO Auto-generated method stub
		for(int i = 0; i < GENERATIONS; ++i) {
			Board fittest = this.population.get(0);
			if(fittest.isGoal())
				return fittest;
			List<Board> newPopulation = new ArrayList<Board>();
			for(int j = 0; j < popSize; ++j) {
				newPopulation.add(generateChild());
			}
			population = newPopulation;
			population.sort(new BoardComparator());
		}
		return this.population.get(0);
	}
	
	public Board generateChild() {
		Board p1 = getParent();
		Board p2 = getParent();
		return crossover(p1, p2);
	}

	/*
	 * Returns a random board from the population
	 */
	public Board getParent() {
		Random random = new Random();
		int index = random.nextInt(FITTEST);	//Selects parent from the fittest population
		return this.population.get(index);
	}
	
	
	public Board crossover(Board p1, Board p2) {
		Random random = new Random();
		int crossoverPoint = random.nextInt(p1.getLength()-2) + 1;
		int length = p1.getLength();
		Board child = new Board(length);
		for(int i = 0; i < length; ++i) {
			if(i < crossoverPoint)
				child.getState()[i] = p1.getState()[i];
			else
				child.getState()[i] = p2.getState()[i];
		}
		mutate(child);
		return child;
	}
	
	/*
	 * Mutates a child based on a predefined mutation rate
	 */
	public void mutate(Board child) {
		Random random = new Random();
		int m = random.nextInt(100);
		int length = child.getLength();
		if(m < this.MUTATION_RATE) 
			child = child.getSuccessor();
	}

}
