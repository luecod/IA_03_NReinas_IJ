import org.jgap.*;
import org.jgap.impl.*;
public class Main {
    static final int MAX_ALLOWED_EVOLUTIONS = 1000;

    public static void main(String[] args) throws Exception {
        Configuration.reset();
        DefaultConfiguration conf = new DefaultConfiguration();

        FitnessFunction myFunc = new ReinasFitnessFunction();
        conf.setFitnessFunction(myFunc);

        Gene[] sampleGenes = new Gene[4];
        sampleGenes[0] = new IntegerGene(conf, 0, 3);
        sampleGenes[1] = new IntegerGene(conf, 0, 3);
        sampleGenes[2] = new IntegerGene(conf, 0, 3);
        sampleGenes[3] = new IntegerGene(conf, 0, 3);

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(2200);

        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = population.getFittestChromosome();

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome candidate = population.getFittestChromosome();

            if (candidate.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidate;
            }
        }

        int[][] board = new int[4][4];

        for (int i = 0; i < bestSolutionSoFar.size(); i++) {
            IntegerGene gene = (IntegerGene) bestSolutionSoFar.getGene(i);
            board[gene.intValue()][i] = 1;
        }

        System.out.println("The best solution has a fitness value of " + bestSolutionSoFar.getFitnessValue());
        System.out.println("It contains the following: ");

        for (int[] row : board) {
            for (int field : row) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
}
