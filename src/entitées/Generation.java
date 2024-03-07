package entitées;

import java.util.List;

/**
 * Représente une génération d'individus caractérisée par un numéro et une liste d'individus.
 * 
 * @inv <pre>
 *     getGeneration() != null
 *     nbIndividus() >= 0
 *     getNumber() >= 0 </pre>
 *     
 * @cons <pre>
 * $ARGS$ int number
 * $PRE$
 *     number >= 0
 * $POST$
 *     getNumber() == number
 *     getGeneration().isEmpty() </pre>   
 */
public interface Generation {
    // REQUETES

    /**
     * Renvoie la liste des individus de la génération.
     */
    List<StdIndividu> getGeneration();

    /**
     * Renvoie le nombre d'individus dans la génération.
     */
    int nbIndividus();

    /**
     * Renvoie le numéro de la génération.
     */
    int getNumber();

    /**
     * Vérifie si la génération contient une solution valide.
     */
    boolean containsSolution();

    // COMMANDES

    /**
     * Définit la liste des individus de la génération.
     * 
     * @pre <pre>
     *     generation != null </pre>
     * @post <pre>
     *     getGeneration() == generation </pre>
     */
    void setGeneration(List<StdIndividu> generation);
    
    /**
     * Ajoute un seul individu à la génération.
     * 
     * @pre <pre>
     *     individu != null </pre>
     * @post <pre>
     *     getGeneration().contain(individu)</pre>
     */
    void addIndividu(StdIndividu individu);
    
    
    /**
     * Ajoute plusieurs individus à la génération.
     * 
     * @pre <pre>
     *     individu != null </pre>
     * @post <pre>
     *     foreach individu in individus:
     *        getGeneration().contain(individu)</pre>
     */
    void addIndividus(List<StdIndividu> individus);

    /**
     * Définit le numéro de la génération.
     * 
     * @pre <pre>
     *     number >= 0 </pre>
     * @post <pre>
     *     getNumber() == number </pre>
     */
    void setNumber(int number);

    /**
     * Sélectionne les meilleurs individus de la génération pour être parents.
     * 
     * @pre <pre>
     *     nbrParents > 0 && nbrParents <= nbIndividus() </pre>
     * @post <pre>
     *     result contient la liste des parents séléctionés
     */
    List<StdIndividu> selectParents(int nbrParents);

    /**
     * Effectue le croisement des individus parents pour créer une nouvelle génération.
     * 
     * @pre <pre>
     *     parents.size() >= 2 </pre>
     * @post <pre>
     *     result contient la liste des parents aprés croisement
     */
    List<StdIndividu> croisementParents(List<StdIndividu> parents);

    /**
     * Effectue la mutation des individus dans la génération.
     * 
     * @pre <pre>
     *     individus != null </pre>
     * @post <pre>
     *     result contient la liste des individus après mutation 
     */
    List<StdIndividu> mutationIndividus(List<StdIndividu> individus);

  
}
