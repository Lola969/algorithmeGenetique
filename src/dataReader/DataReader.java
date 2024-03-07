package dataReader;

import java.util.List;

/**
 * Une interface représentant un lecteur de données capable de lire des informations sur les produits à partir d'un fichier CSV.
 * @inv <pre>
 *     filename != null
 *     nbProducts >= 0
 *     products != null </pre>
 *     
 * @cons <pre>
 * $ARGS$ String filename, int nbProducts
 * $PRE$
 *     filename != null
 *     nbProducts > 0
 * $POST$
 *     filename().equals(filename)
 *     nbProducts() == nbProducts
 *     products().size() == nbProducts
 * </pre>
 *      
 */
public interface DataReader {
	
    // REQUETES

    /**
     * Renvoie la liste des produits lus à partir du fichier.
     */
    List<StdProduct> getProducts();

    /**
     * Renvoie la liste des poids des produits.
     */
    List<Double> getWeights();

    /**
     * Renvoie la liste des volumes des produits.
     */
    List<Double> getVolumes();

    /**
     * Renvoie la liste des prix des produits.
     */
    List<Integer> getPrices();

    /**
     * Renvoie la liste des quantités des produits.
     */
    List<Integer> getQuantities();


    // COMMANDES

    /**
     * Modifie le nom du fichier à partir duquel les données sont lues.
     * @pre <pre>
     *     filename != null </pre>
     * @post <pre>
     *     Le nom du fichier est mis à jour avec la valeur spécifiée. </pre>
     * @param filename Le nouveau nom du fichier.
     */
    void setFileName(String filename);

    /**
     * Met à jour la liste des produits en lisant les données à partir du fichier
     * @post <pre>
     *     La liste des produits est mise à jour avec les données lues à partir du fichier spécifié. </pre>
     */
    void setProducts();
	

	

}
