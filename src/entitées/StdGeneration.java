package entitées;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class StdGeneration implements Generation{
	
	// ATRRIBUTS
	
	private int number;
	private List<StdIndividu> generation;
	
	
	// CONSTRUCTEUR
	
	public StdGeneration(int number) {
		generation = new ArrayList<StdIndividu>();
		this.number = number;
	}
	
	// REQUETE
	
	public List<StdIndividu> getGeneration() {
		return generation;
	}
	
	public int nbIndividus() {
		return generation.size();
	}
	
	public int getNumber() {
		return number;
	}
	

	public boolean containsSolution() {
		for(StdIndividu individu : generation) {
			if(individu.isSolution())
				return true;
		}
		return false;
	}
	
	// COMMANDE
	
	public void setGeneration(List<StdIndividu> generation) {
	    if (generation == null) {
	        throw new IllegalArgumentException("generation ne doit pas pas être null ");
	    }
	}
	
	public void addIndividu(StdIndividu individu) {
		if(individu == null) {
			throw new IllegalArgumentException("individu ne doit pas pas être null ");
		}
		generation.add(individu);
	}
	
	public void addIndividus(List<StdIndividu> individus) {
		if(individus == null) {
			throw new IllegalArgumentException("individus ne doit pas pas être null ");
		}
		for (StdIndividu individu :individus ) {
			addIndividu(individu);
		}
	}
	
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	

	public List<StdIndividu> selectParents(int nbrParents) {
		
		if(nbrParents <=0 ) {
			throw new IllegalArgumentException("le nombre de parents doit être strictement positif  ");
		}
	    Collections.sort(generation, new Comparator<StdIndividu>() {
	        @Override
	        public int compare(StdIndividu individu1, StdIndividu individu2) {
	            return Double.compare(individu2.getfitnessValue(), individu1.getfitnessValue());
	        }
	    });

	    return generation.subList(0, nbrParents);
	}


	  public List<StdIndividu> croisementParents(List<StdIndividu> parents){
	        if (parents.size() < 2) {
	        	throw new IllegalArgumentException("La génération ne contient pas suffisamment d'individus pour le croisement.");
	        }

	        List<StdIndividu> nouvelleGeneration = new ArrayList<StdIndividu>();

	        // Parcours des paires de parents
	        for (int i = 0; i < parents.size() - 1; i += 2) {
	            StdIndividu parent1 = parents.get(i);
	            StdIndividu parent2 = parents.get(i + 1);

	            StdIndividu enfant1 = croisementIndiv(parent1, parent2);
	            StdIndividu enfant2 = croisementIndiv(parent2, parent1);

	            nouvelleGeneration.add(enfant1);
	            nouvelleGeneration.add(enfant2);
	        }

	        // Si le nombre de parents est impair, ajoute le dernier parent sans le croiser
	        if (parents.size() % 2 != 0) {
	            nouvelleGeneration.add(parents.get(parents.size() - 1));
	        }

	        return nouvelleGeneration;
	    }
	
	
	public List<StdIndividu> mutationIndividus(List<StdIndividu> individus){
		if(individus == null) {
			throw new IllegalArgumentException("individus ne doit pas pas être null ");
		}
		List<StdIndividu> newGeneration = new ArrayList<StdIndividu>();
		StdIndividu newIndividu;
		for (StdIndividu individu : individus) {
			newIndividu = mutationIndiv(individu);
			newGeneration.add(newIndividu);
		}
		return newGeneration ; 
	}
	

	

	
	//OUTILS 
	
	
	public static StdGeneration createInitialGeneration(int taille, int productNumber, List<Integer> quantitiesMax) {
		if(taille <= 0 || productNumber <=0 || quantitiesMax == null ) {
			throw new IllegalArgumentException();
		}
		
		StdGeneration initialGeneration = new StdGeneration(0);
		
		for(int i = 0; i < taille; i++) {
			StdIndividu individu = new StdIndividu(productNumber, quantitiesMax);
			
			initialGeneration.addIndividu(individu);
		}
		return initialGeneration;
	}
	
	   private StdIndividu croisementIndiv(StdIndividu parent1 ,StdIndividu parent2) {
		   if( parent1 == null || parent2 == null) {
			   throw new IllegalArgumentException();
		   }
		
		 List<Integer> genesParent1 = parent1.getIndividu();
	        List<Integer> genesParent2 = parent2.getIndividu();

	        List<Integer> childIndividu = new ArrayList<Integer>();

	       
	        for (int i = 0; i < genesParent1.size(); i++) {
	        	if(i % 2 == 0) {
	        	childIndividu.add(genesParent1.get(i));
	        	}else {
	        		childIndividu.add(genesParent2.get(i));
	        	}
	        }

	      
	       StdIndividu newIndividu = new StdIndividu(childIndividu);
	       return newIndividu ;
	    }

	
	
	      private StdIndividu mutationIndiv(StdIndividu individu) {
			if(individu == null ) {
				   throw new IllegalArgumentException();
			  }
	        Random random = new Random();
	        int i = random.nextInt(individu.getIndividu().size());
	        int j = random.nextInt(individu.getIndividu().size());
	        return swap(individu, i, j);
	    }

	    private StdIndividu swap(StdIndividu individu, int i, int j) {
	        List<Integer> genes = individu.getIndividu();
	        int temp = genes.get(i);
	        genes.set(i, genes.get(j));
	        genes.set(j, temp);
	        return new StdIndividu(genes);
	    }
}


