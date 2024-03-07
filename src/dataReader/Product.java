package dataReader;


/**
 * Un produit caractérisé par un identifiant, un nom, un poids, un volume, un prix et une quantité.
 * @inv <pre>
 *     getId() >= 0
 *     getName() != null
 *     getWeight() >= 0
 *     getVolume() >= 0
 *     getPrice() >= 0
 *     getQuantity() >= 0 </pre>
 *     
 *     
 * @cons <pre>
 * $ARGS$ int id, String name, double weight, double volume, int price, int quantity
 * $PRE$
 *     id >= 0
 *     name != null
 *     weight >= 0
 *     volume >= 0
 *     price >= 0
 *     quantity >= 0
 * $POST$
 *     getId() == id
 *     getName() == name
 *     getWeight() == weight
 *     getVolume() == volume
 *     getPrice() == price
 *     getQuantity() == quantity </pre>   
 */

public interface Product {
	// REQUETES

    /**
     * L'identifiant du produit.
     */
    int getId();

    /**
     * Le nom du produit.
     */
    String getName();

    /**
     * Le poids du produit.
     */
    double getWeight();

    /**
     * Le volume du produit.
     */
    double getVolume();

    /**
     * Le prix du produit.
     */
    int getPrice();

    /**
     * La quantité du produit.
     */
    int getQuantity();

    // COMMANDES

    /**
     * Définit le poids du produit.
     * @pre <pre>
     *     weight >= 0 </pre>
     * @post <pre>
     *     getWeight() == weight </pre>
     */
    void setWeight(double weight);

    /**
     * Définit le volume du produit.
     * @pre <pre>
     *     volume >= 0 </pre>
     * @post <pre>
     *     getVolume() == volume </pre>
     */
    void setVolume(double volume);

    /**
     * Définit le prix du produit.
     * @pre <pre>
     *     price >= 0 </pre>
     * @post <pre>
     *     getPrice() == price </pre>
     */
    void setPrice(int price);

    /**
     * Définit la quantité du produit.
     * @pre <pre>
     *     quantity >= 0 </pre>
     * @post <pre>
     *     getQuantity() == quantity </pre>
     */
    void setQuantity(int quantity);
}
