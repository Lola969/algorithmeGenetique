package entitées;

import java.util.List;

/**
 * Représente un individu caractérisé par une liste de produits, une valeur totale,
 * un poids total, un volume total et une valeur fitness.
 * 
 * @inv <pre>
 *     getIndividu() != null
 *     getValue() >= 0
 *     getWeight() >= 0
 *     getVolume() >= 0
 *     getfitnessValue() >= 0 </pre>
 *     
 * @cons <pre>
 * $ARGS$ List<Integer> individu, double value, double weight, double volume, double fitnessValue
 * $PRE$
 *     individu != null
 *     value >= 0
 *     weight >= 0
 *     volume >= 0
 *     fitnessValue >= 0
 * $POST$
 *     getIndividu() == individu
 *     getValue() == value
 *     getWeight() == weight
 *     getVolume() == volume
 *     getfitnessValue() == fitnessValue </pre>   
 */
public interface Individu {
    // REQUETES

    /**
     * Renvoie la liste des produits de l'individu.
     */
    List<Integer> getIndividu();

    /**
     * Renvoie la valeur totale de l'individu.
     */
    double getValue();

    /**
     * Renvoie le poids total de l'individu.
     */
    double getWeight();

    /**
     * Renvoie le volume total de l'individu.
     */
    double getVolume();

    /**
     * Renvoie la valeur fitness de l'individu.
     */
    double getfitnessValue();
    
    
    /**
     * Indique si l'individu est une solution du problème.
     */
    public boolean isSolution();
    
    /**
     * marque l'individu comme solution possible de problème.
     */
    void setIsSolution();

    // COMMANDES



    /**
     * Définit le poids total de l'individu en fonction des poids des produits.
     * 
     * @pre <pre>
     *     list != null </pre>
     * @post <pre>
     *     getWeight() == weight </pre>
     */
    void setWeight(List<Double> weightProducts);

    /**
     * Définit le volume total de l'individu en fonction des volumes des produits.
     * 
     * @pre <pre>
     *     list != null </pre>
     * @post <pre>
     *     getVolume() == volume </pre>
     */
    void setVolume(List<Double> volumeProducts);

    /**
     * Définit la valeur fitness de l'individu.
     * 
     * @pre <pre>
     *     max_weight > 0 &&  maxVolume >0 </pre>
     * @post <pre>
     *     getfitnessValue() == fitnessValue </pre>
     */
    void setFitness(double max_weight, double maxVolume);
}
