package affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class AlgoGen {

	// ATTRIBUTS

    private final JFrame frame;
    //private final Map<BKey, JButton> buttons;
    //private final JSlider slider;
    private final JTextArea textArea;
    private JComboBox<Integer> productsNbBox;
    private final JCheckBox[] conditionsAllower;
    private final JTextField[] camionTextFields;
    private final JTextField[] conditionsTextFields;
   

    // CONSTRUCTEURS

    public AlgoGen() {
        // MODELE
        //animator = m;
        // VUE
    	createView();
        frame = new JFrame();
        textArea = new JTextArea(20, 40);
        productsNbBox = new JComboBox<Integer>();
        Integer[] splitNb = new Integer[5];
    	for(int i = 0; i < 5; i++) {
    		splitNb[i] = i + 10;
    	} 
    	DefaultComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel<Integer>(splitNb);
    	productsNbBox.setModel(comboBoxModel);	
    	
    	
        String[] conditions = {"Autoriser nbIter", "Autoriser temps d'exec", "Autoriser score min"};
        
        conditionsAllower = new JCheckBox[3];
        conditionsTextFields = new JTextField[3];
        
        for (int i = 0; i < conditions.length; i++) {
            conditionsTextFields[i] = new JTextField();
            conditionsAllower[i] = new JCheckBox(conditions[i]);
        }
        
        camionTextFields = new JTextField[2];
        camionTextFields[0] = new JTextField();
        camionTextFields[1] = new JTextField();
        
        //animable = v;
        placeComponents();
        // CONTROLEUR
        connectControllers();
    }

    // COMMANDES

    public void display() {  
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS

    private void createView() {
    	
		
	}
    
    
    private void placeComponents() {
    	JPanel p = new JPanel();
        { //--
        	p.add(new JLabel("remplir ces champs avant de lancer l'appli"));
        } //--
        frame.add(p, BorderLayout.NORTH);
    	
        //new FlowLayout(FlowLayout.LEFT)
    	p = new JPanel(new GridLayout(0, 1));
        { //--
        	
        	JPanel q = new JPanel(new GridLayout(3, 0));
        	{ //--
        		q.add(new JLabel("carctéristiques du camion"));
        		
        		JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        		{ //--
					r.add(new JLabel("poids"));
		    		r.add(new JTextField()).setPreferredSize(new Dimension(100, 20));
        		} //--
        		q.add(r);
        		
        		r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        		{ //--
		    		r.add(new JLabel("volume"));
		    		r.add(new JTextField()).setPreferredSize(new Dimension(100, 20));
        		} //--
        		q.add(r);
        		
        		q.setBorder(BorderFactory.createEtchedBorder());
        		
        	} //--
    		p.add(q);
    		
    		
    		
    		q = new JPanel(new GridLayout(0, 1));
    		{ //--
    			q.add(new JLabel("conditions à prendre en compte"));
    			
    			for(int i = 0; i < 3; i++) {
    					q.add(conditionsAllower[i]);
    					//r.add(new JLabel(conditions[i]));
    					q.add(new JTextField());
	    				
	    		}
        		q.setBorder(BorderFactory.createEtchedBorder());
	        	
        	} //--
    		
    		p.add(q);
    		
    		q = new JPanel();
    		{ //--
    			q.add(new JLabel("nb de produits à utiliser"));
        		q.add(productsNbBox);
	        	
        	} //--
    		p.add(q);
        } //--
        frame.add(p, BorderLayout.WEST);

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        p = new JPanel();
        { //--
        	p.add(new JButton("Lancer"));
        } //--
        frame.add(p, BorderLayout.SOUTH);
    }

    private void connectControllers() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
    
    // POINT D'ENTREE
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlgoGen().display();
            }
        });
    }
 
}
