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
	}
	
	@Override
	public void solve(Board board) {
		// TODO Auto-generated method stub
		for(int i = 0; i < GENERATIONS; ++i) {
			List<Board> newPopulation = new ArrayList<Board>();
			for(int j = 0; j < popSize; ++j) {
				newPopulation.add(generateChild());
			}
		}
		
	}
	
	/*
	 * Returns a random board from the population
	 */
	public Board getParent() {
		Random random = new Random();
		int index = random.nextInt(popSize);
		return this.population.get(index);
	}
	
	public Board generateChild() {
		Board p1 = getParent();
		Board p2 = getParent();
		return crossover(p1, p2);
	}
	
	public Board crossover(Board p1, Board p2) {
		Random random = new Random();
		int crossoverPoint = random.nextInt(p1.getLength()-1);
		Board child = new Board(p1.getLength());
		child.setQueens(p1.getQueens(), crossoverPoint);
		child.setQueens(p2.getQueens(), crossoverPoint+1, p2.getLength());
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
		if(m < this.MUTATION_RATE) {
			int mutationindex = random.nextInt(length);
			Queen queen = child.getQueens().get(mutationindex);
			int change = random.nextInt(2);
			if(change == 1)
				queen.setX(random.nextInt(length));
			change = random.nextInt(2);
			if(change == 1)
				queen.setY(random.nextInt(length));
		}
	}

}
