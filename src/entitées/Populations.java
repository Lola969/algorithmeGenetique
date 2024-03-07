package entitées;

import java.util.Map;

/**
 * Représente une collection de générations, caractérisée par une map associant
 * le numéro de la génération à une instance de la classe StdGeneration.
 * 
 * @inv <pre>
 *     nbGenerations() >= 0
 *     getPopulations() != null </pre>
 *     
 * @cons <pre>
 * $ARGS$
 * $PRE$ </pre>
 * $POST$
 *     nbGenerations() == 0
 *     getPopulations().isEmpty()
 *     getBestSolution() == null
 *     getBestGenerationNumber() == -1 </pre>   
 */
public interface Populations {
	
    // REQUETES

    /**
     * Renvoie le nombre de générations dans la collection.
     */
    int nbGenerations();

    /**
     * Renvoie la map associant le numéro de la génération à une instance de StdGeneration.
     */
    Map<Integer, StdGeneration> getPopulations();

    /**
     * Renvoie la meilleure solution trouvée dans toutes les générations.
     */
    StdIndividu getBestSolution();

    /**
     * Renvoie le numéro de la génération où la meilleure solution a été trouvée.
     */
    int getBestGenerationNumber();

    // COMMANDES

    /**
     * Ajoute une nouvelle génération à la collection.
     * 
     * @pre <pre>
     *     generation != null </pre>
     * @post <pre>
     *     nbGenerations() == old(nbGenerations()) + 1
     *     getPopulations().containsKey(generation.getNumber()) </pre>
     */
    void addGeneration(StdGeneration generation);
}

