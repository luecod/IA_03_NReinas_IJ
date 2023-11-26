import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class ReinasFitnessFunction extends FitnessFunction {

    private static final long serialVersionUID = 1L;

    @Override
    protected double evaluate(IChromosome cromosoma) {
        double aptitud = 0;

        // Recorrer cada gen (columna de la reina)
        for (int i = 0; i < cromosoma.size(); i++) {
            int posicionReina = (Integer) cromosoma.getGene(i).getAllele();

            // Comparar con los demás genes (columnas)
            for (int j = 0; j < cromosoma.size(); j++) {
                if (i != j) {
                    int posicionOtraReina = (Integer) cromosoma.getGene(j).getAllele();

                    // Si las reinas no están en la misma fila ni en diagonal, aumentar la aptitud
                    if (posicionReina != posicionOtraReina && Math.abs(i - j) != Math.abs(posicionReina - posicionOtraReina)) {
                        aptitud++;
                    }
                }
            }
        }

        return aptitud;
    }
}
