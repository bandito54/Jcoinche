package graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import cards.*;
import client.*;
import server.*;

public class CreateGui extends JFrame
{
	static JFrame frame = new JFrame("JCoinche");//CREATION DE MA FRAME
	public static void createAndShowGUI(Cards deck)
	{
		//PARAMETRES DE LA FENETRE
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//FERME LE PROGRAMME SI ON FERME LA FENETRE
		frame.setLocation(450, 250);//POSITIONNE LA FENETRE PAR RAPPORT A L'ECRAN
		frame.setResizable(false);//EMPECHE DE REDIMENSIONNER LA FENETRE
		frame.setIconImage(new ImageIcon("C:\\Users\\emmanueladrian\\Pictures\\Jcoinche\\play.jpg").getImage());//MINIATURE DE LA FENETRE
		GridLayout	gl	=	new GridLayout(); //CREATION D'UN LAYOUT EN QUADRILLAGE
		gl.setColumns(3);//NOMBRE DE COLONNES
		gl.setRows(1);//NBR DE LIGNES
		gl.setHgap(10); //Cinq pixels d'espace entre les colonnes (Horizontal)
		gl.setVgap(10); //Cinq pixels d'espace entre les lignes (Vertical)
		frame.setLayout(gl);
		// Display the window.
		frame.setVisible(true);//AFFICHE LA FENETRE
		frame.setSize(800, 315);//DONNE LES DIMENSIONS DE LA FENETRE
	}

	public static void contentGUI(Cards deck)
	{
		//ENCART DU JOUEUR 1
		JLabel deck1 = new JLabel();
		int total = Cards.D1.size() + Cards.D2.size();
		int cartes_J2 = 32 - total;
		deck1.setText("  Nombre de carte du J1 = " + total);
		frame.add(deck1);
	    //ENCART DU JOUEUR 2
	    JLabel deck2 = new JLabel();
		deck2.setText("Nombre de carte du J2 = " + cartes_J2);
		frame.add(deck2);
		//TRAITEMENT DU BOUTON DE JEU
		JButton playButton = new JButton("PLAY", new ImageIcon("C:\\Users\\emmanueladrian\\Pictures\\Jcoinche\\miniature.png"));//CREATION DU BOUTON PLAY
		playButton.setVerticalTextPosition(AbstractButton.BOTTOM);//POSITION VERTICALE DU TEXTE
		playButton.setHorizontalTextPosition(AbstractButton.CENTER);//POSITION HORIZONTALE DU TEXTE
		playButton.setMnemonic(KeyEvent.VK_ENTER);
		playButton.setActionCommand("enable");
		playButton.setEnabled(true);
	    frame.getContentPane().add(playButton, BorderLayout.CENTER);//MISE EN PLACE DU BOUTON AU CENTRE DE LA FRAME
	}
}