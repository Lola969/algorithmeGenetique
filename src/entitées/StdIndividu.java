package entitées;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StdIndividu implements Individu {
	
	// ATTRIBUTS 
	
	private List<Integer> individu;
	private int nbProducts;
	private double weight;
	private double value;
	private double volume;
	private double fitnessValue ;
	private boolean isSolution;
	
	// CONSTRUCTEUR 
	
	public StdIndividu(int nbProducts, List<Integer> quantitiesMax) {
		if(nbProducts < 1) {
			throw new AssertionError();
		}
		individu = generate_individual(nbProducts, quantitiesMax);
	}
	
	public StdIndividu(List<Integer> individu) {
		this.individu = individu ;
	}
	
	// REQUETES
	
	public int getNbProducts() {
		return nbProducts;
	}
	
	public List<Integer> getIndividu() {
		return individu;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public  double getValue() {
		return value;
	}

    public double getVolume() {
        return volume;
    }


	public double getfitnessValue() {
		return fitnessValue;
	}
	
	public boolean isSolution() {
		return isSolution; 
	}
	
	// COMMANDES
	public void setIsSolution() {
		isSolution = true ;
	}


	public void setWeight(List<Double> weightProducts) {
	    if (weightProducts.size() != individu.size()) {
	        throw new IllegalArgumentException("Les listes weightProducts et individu doivent avoir la même taille");
	    }
	    weight= 0;
	    for (int i = 0; i < individu.size(); i++) {
	        int quantity = individu.get(i);
	        double weightp =weightProducts.get(i);
	        weight += quantity * weightp;
	    }
	}
	
	
	public void setVolume(List<Double> volumeProducts) {
		   if (volumeProducts == null) {
		        throw new IllegalArgumentException("valueProducts ne doit pas être null");
		    }

	    volume= 0;
	    for (int i = 0; i < individu.size(); i++) {
	        int quantity = individu.get(i);
	        double volumep = volumeProducts.get(i);
	        volume += quantity * volumep;
	    }
	}
	
	
	
	public void setValue(List<Integer> valueProducts) {
	    if (valueProducts == null) {
	        throw new IllegalArgumentException("valueProducts ne doit pas être null");
	    }

	    value = 0;
	    for (int i = 0; i < individu.size(); i++) {
	        int quantity = individu.get(i);
	        int price = valueProducts.get(i);
	        value += quantity * price;
	    }
	}

    public void setFitness(double max_weight, double max_Volume) {
    	
    	if (max_weight <= 0 || max_Volume <= 0 ) {
    		throw new IllegalArgumentException("max_weight et max_Volume  doivent être strictement positifs"); 
    	}
    	
        if (getWeight() > max_weight || getVolume() > max_Volume) {
            // Si le poids ou le volume dépasse la limite, attribuer une fitness basée sur la différence
            double excessWeight = Math.max(0, getWeight() - max_weight); // Calculer l'excès de poids
            double excessVolume = Math.max(0, getVolume() - max_Volume); // Calculer l'excès de volume
            double fitnessValueWeight = max_weight - excessWeight; // Fitness basée sur le poids
            double fitnessValueVolume = max_Volume - excessVolume; // Fitness basée sur le volume
            fitnessValue = Math.min(fitnessValueWeight, fitnessValueVolume); // Choisir le minimum des deux
        } else {
            // Si le poids et le volume sont dans les limites, calculer la fitness normalement
                fitnessValue = getValue() / getWeight();
            }
        
    }

	
	// OUTILS
	
	private List<Integer> generate_individual(int nbr_products, List<Integer> quantitiesMax) {
		assert quantitiesMax.size() == nbr_products;
		
        List<Integer> individu = new ArrayList<Integer>(nbr_products);
        Random random = new Random();

        for (int i = 0; i < nbr_products; i++) {
            individu.add(random.nextInt(quantitiesMax.get(i)));
        }

        return individu;
    }





	

}


