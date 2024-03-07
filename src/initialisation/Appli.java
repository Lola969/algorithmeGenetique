package initialisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import dataReader.StdDataReader;
import entitées.StdGeneration;
import entitées.StdIndividu;
import entitées.StdPopulations;

public class Appli {
    
    public static void main(String[] args) {
        // Lecture des données à partir du fichier CSV
        StdDataReader dataReader = new StdDataReader("src/data.csv", 8);

        // Récupération des quantités de produits
        List<Integer> quantities = dataReader.getQuantities();
        
        // Lecture des préférences depuis le fichier properties
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/preferences.properties"));
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier entré n'existe pas ou n'est pas accessible en lecture : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
        }
        
        // Extraction des paramètres
        int taille = Integer.parseInt(properties.getProperty("nbIndividualsPerGeneration"));
        int nbIndividuals = Integer.parseInt(properties.getProperty("nbProductsPerIndividual"));
        int nbParents = Integer.parseInt(properties.getProperty("nbGenerationPrents"));
        int nbIterations = 2;

        double maxCapacity = Double.parseDouble(properties.getProperty("MAXIMUM_LOAD_CAPACITY"));
        double maxVolume = Double.parseDouble(properties.getProperty("MAXIMUM_LOAD_VOLUME"));

        
        // Initialisation des populations
        StdPopulations populations = new StdPopulations ();
        
        // Création de la première génération
        StdGeneration gen = StdGeneration.createInitialGeneration(taille, nbIndividuals, quantities);
        populations.addGeneration(gen);
        
        // Affichage des individus et de leurs caractéristiques
        System.out.println("Individus:");
        for (StdIndividu individu : gen.getGeneration()) {
            List<Integer> produits = individu.getIndividu();
            System.out.println("Individu " + gen.getGeneration().indexOf(individu) + ":");
            for (int i = 0; i < produits.size(); i++) {
                System.out.println("    Produit " + i + ": Quantité = " + produits.get(i));
            }
            
        	individu.setValue(dataReader.getPrices());
            System.out.println("    Valeur totale de l'individu : " + individu.getValue());
            
            individu.setWeight(dataReader.getWeights());
            System.out.println("    Poids total de l'individu : " + individu.getWeight());
            
            individu.setVolume(dataReader.getVolumes());
            System.out.println("    Volume total de l'individu : " + individu.getVolume());
            
            individu.setFitness(maxCapacity, maxVolume);
            System.out.println("    Valeur fitness : " + individu.getfitnessValue());
            System.out.println(); 
        }
        
        StdGeneration newGeneration = gen;
        
        // Boucle principale d'itérations pour l'algorithme génétique
        for (int j = 0; j < nbIterations; j++) {
            // Sélection des parents pour la prochaine génération
            List<StdIndividu> parents = newGeneration.selectParents(nbParents);
            
            // Affichage des parents sélectionnés
            System.out.println("Parents sélectionnés:");
            for (StdIndividu individu : parents) {
                List<Integer> parent = individu.getIndividu();
                System.out.println("Parent " + newGeneration.getGeneration().indexOf(individu) + ":");
                for (int i = 0; i < parent.size(); i++) {
                    System.out.println("    Produit " + i + ": Quantité = " + parent.get(i));
                }
                System.out.println("    Valeur fitness : " + individu.getfitnessValue());
                System.out.println();
            }
            
            // Croisement des parents pour générer des enfants
            List<StdIndividu> childs = newGeneration.croisementParents(parents);
            
            // Affichage des enfants générés
            System.out.println("Enfants aprés croisement:");
            int k = 0;
            for (StdIndividu individu : childs) {
                List<Integer> child = individu.getIndividu();
                System.out.println("Enfant " + k + ":");
                for (int i = 0; i < child.size(); i++) {
                    System.out.println("    Produit " + i + ": Quantité = " + child.get(i));
                }
                k++;
                System.out.println();
            }
            
            // Mutation des parents et des enfants
            List<StdIndividu> mutParents = newGeneration.mutationIndividus(parents);
            List<StdIndividu> mutchilds = newGeneration.mutationIndividus(childs);
            
            // Évaluation des nouveaux individus après mutation
            for(StdIndividu child : mutchilds) {
            	
				child.setValue(dataReader.getPrices());
                child.setWeight(dataReader.getWeights());
                child.setVolume(dataReader.getVolumes());
                child.setFitness(maxCapacity,maxVolume);
            }
            
            for(StdIndividu parent : mutParents) {
            	parent.setValue(dataReader.getPrices());
            	parent.setWeight(dataReader.getWeights());
                parent.setVolume(dataReader.getVolumes());
                parent.setFitness(maxCapacity,maxVolume);
            }
            
            // Affichage des enfants après mutation
            System.out.println("Enfants après mutation:");
            int n = 0;
            for (StdIndividu individu : mutchilds) {
                List<Integer> child = individu.getIndividu();
                System.out.println("Individu " + n + ":");
                for (int i = 0; i < child.size(); i++) {
                    System.out.println("    Produit " + i + ": Quantité = " + child.get(i));
                }
                n++;
                System.out.println();
            }
            
            // Création de la nouvelle génération
            newGeneration = new StdGeneration(j + 1);
            newGeneration.addIndividus(mutParents);
            newGeneration.addIndividus(mutchilds);
            populations.addGeneration(newGeneration);
            
            // Affichage des populations après chaque itération
            afficherPopulations(populations);
        }
        
        // Affichage de la meilleure solution trouvée
        System.out.println("Meilleure solution trouvée :");
        List<Integer> produits = populations.getBestSolution().getIndividu();
        for (int i = 0; i < produits.size(); i++) {
            System.out.println("Produit " + i + ": Quantité = " + produits.get(i));
        }
        System.out.println("Numéro de la génération de la meilleure solution : " + populations.getBestGenerationNumber());
        System.out.println("Valeur totale de la meilleure solution : " + populations.getBestSolution().getValue());
        System.out.println("Poids total de la meilleure solution : " + populations.getBestSolution().getWeight());
        System.out.println("Volume total de la meilleure solution : " + populations.getBestSolution().getVolume());
        System.out.println("Valeur fitness de la meilleure solution : " + populations.getBestSolution().getfitnessValue());
    }
    
    // Méthode pour afficher les populations à chaque itération
    public static void afficherPopulations(StdPopulations populations) {
        System.out.println("Populations après itération :");
        for (Map.Entry<Integer, StdGeneration> entry : populations.getPopulations().entrySet()) {
            StdGeneration gen = entry.getValue();
            System.out.println("Génération "+ gen.getNumber()+":");
            for (StdIndividu individu : gen.getGeneration()) {
                List<Integer> produits = individu.getIndividu();
                System.out.println("    Individu " + gen.getGeneration().indexOf(individu) + ":");
                for (int i = 0; i < produits.size(); i++) {
                    System.out.println("        Produit " + i + ": Quantité = " + produits.get(i));
                }
               

                System.out.println("        Valeur totale de l'individu : " + individu.getValue());
                System.out.println("        Poids total de l'individu : " + individu.getWeight());
                System.out.println("        Volume total de l'individu : " + individu.getVolume());
                System.out.println("        Valeur fitness : " + individu.getfitnessValue());
                System.out.println();
            }
        }
    }
}
