package dataReader;

public class StdProduct implements Product {
	
    private int id;
    private String name;
    private double weight;
    private double volume;
    private int price;
    private int quantity;

    public StdProduct(int id, String name, double weight, double volume, int price, int quantity) {
    	if(id < 0 || name == null || weight < 0 || volume < 0  || price < 0 || quantity < 0 ) {
    		throw new AssertionError();
    	}
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.price = price;
        this.quantity = quantity;
    }

    //REQUETES

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    
    //COMMANDES
    
    
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("L'identifiant doit être un entier positif ou nul.");
        }
        this.id = id;
    }

  
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Le nom ne peut pas être nul.");
        }
        this.name = name;
    }

   
    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Le poids doit être un nombre positif ou nul.");
        }
        this.weight = weight;
    }


    public void setVolume(double volume) {
        if (volume < 0) {
            throw new IllegalArgumentException("Le volume doit être un nombre positif ou nul.");
        }
        this.volume = volume;
    }

   
    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Le prix doit être un entier positif ou nul.");
        }
        this.price = price;
    }

   
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("La quantité doit être un entier positif ou nul.");
        }
        this.quantity = quantity;
    }
}


