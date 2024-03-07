package dataReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class StdDataReader implements DataReader {

	private String filename; 
	private List<StdProduct> products;
	private int nbProducts;
	
	// CONSTRUCTEUR
	
	public StdDataReader(String filename, int nbProducts) {
		if (filename == null || nbProducts <= 0) {
		        throw new IllegalArgumentException("filename ne doit pas être null et nbProducts doit être positif");
		}
		this.nbProducts = nbProducts;
		this.filename = filename;
		products = readProducts(filename);
	}
	
	// REQUETES
	
	public List<StdProduct> getProducts() {
        return products;
    }
	
	public List<Double> getWeights() {
        List<Double> weights = new ArrayList<Double>();
        for (StdProduct product : products) {
            weights.add(product.getWeight());
        }
        return weights;
    }
	
	public List<Double> getVolumes() {
        List<Double> volumes = new ArrayList<Double>();
        for (StdProduct product : products) {
            volumes.add(product.getVolume());
        }
        return volumes;
    }
	
	public List<Integer> getPrices() {
        List<Integer> prices = new ArrayList<Integer>();
        for (StdProduct product : products) {
            prices.add(product.getPrice());
        }
        return prices;
    }
	
	public List<Integer> getQuantities() {
        List<Integer> weights = new ArrayList<Integer>();
        for (StdProduct product : products) {
            weights.add(product.getQuantity());
        }
        return weights;
    }
	
	
	// COMMANDES
	
	public void setFileName(String filename) {
		if (filename == null) {
	        throw new IllegalArgumentException("filename ne doit pas être null");
	    }
		this.filename = filename;
	}
	
	public void setProducts() {
		products = readProducts(filename);
	}
	
	// OUTILS
	
	private static int countLines(String filename) {
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier entré n'existe pas ou n'est pas accessible en lecture : " + e.getMessage());
		}
		
        int lineCount = 0;
        try {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
        	System.err.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
			e.printStackTrace();
		} finally {
            try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Erreur lors de la fermeture du BufferedReader : " + e.getMessage());
			}
        }
        return lineCount;
    }
	
	private List<StdProduct> readProducts(String filename) {
		List<StdProduct> products = new ArrayList<StdProduct>();
        BufferedReader bufferedReader = null;
        try {
        	bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            int i = 0;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null && i < nbProducts) {
                String[] parts = line.split(",");
                
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                Double weight = Double.parseDouble(parts[2].trim());
                Double volume = Double.parseDouble(parts[3].trim());
                int price = Integer.parseInt(parts[4].trim());
                int quantity = Integer.parseInt(parts[5].trim());
                
                StdProduct product = new StdProduct(id, name, weight, volume, price, quantity);
                products.add(product);
                i++;
            }
        } catch (java.io.FileNotFoundException e) {
    		System.err.println("Le fichier entré n'existe pas ou n'est pas accessible en lecture : " + e.getMessage());
    	} catch (IOException e) {
    	    System.err.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
    	} finally {
    		try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du BufferedReader : " + e.getMessage());
            }
		}
        
        return products;
    }
	
	public static void main(String[] args) {
        StdDataReader dataReader = new StdDataReader("src/data.csv", 20);

        List<Double> weights = dataReader.getWeights();

        for (Double productWeight : weights) {
            System.out.println(productWeight);
        }
    }
}



