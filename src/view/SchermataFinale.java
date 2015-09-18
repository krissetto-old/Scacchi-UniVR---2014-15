package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


class SchermataFinale extends JFrame {

	SchermataFinale() {
		
		//Layout principale
		JPanel borderl = new JPanel();
		borderl.setLayout(new BorderLayout());
		
		//Bottone per selezionare la dama normale
		JButton NuovaPartita = new JButton("Nuova Partita");
		NuovaPartita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Scacchi.avviaGioco();
				dispose();
			}
		});
		
		//Bottone per selezionare la dama pokemon
		JButton Esci = new JButton("Esci");
		Esci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		//Layout bottoni
		JPanel zonaBottoni = new JPanel();
		zonaBottoni.setLayout(new FlowLayout(FlowLayout.CENTER));
		zonaBottoni.add(NuovaPartita);
		zonaBottoni.add(Esci);

		//Layout titolo
		JPanel sopra = new JPanel();
		sopra.setLayout(new BoxLayout(sopra, BoxLayout.Y_AXIS));

		//Titolo
		JTextPane presentazione = new JTextPane();
		presentazione.setText("\n-- Partita Terminata -- \n\n"
				+ "Vuoi iniziare una nuova partita? \n \n");

		presentazione.setEditable(false);

		//Centro il testo all'interno di presentazione
		StyledDocument doc = presentazione.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center1, false);

		sopra.add(presentazione);

		borderl.add(sopra, BorderLayout.NORTH);
		borderl.add(zonaBottoni, BorderLayout.SOUTH);

		//Inizializzo la finestra..
		setContentPane(borderl);
		setTitle("Schermata Finale");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();

		//.. e la centro
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
}