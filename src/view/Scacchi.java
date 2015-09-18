package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import controller.Gioco;
import controller.Mossa;

public class Scacchi extends JFrame {
	
	protected Point click1 = null;
	protected Point click2 = null;
	protected JButton p1 = null;
	protected JButton p2 = null;
	protected Gioco dash;
	
	int BIANCO = 1;
	int NERO = 2;
	
	// Costruttore
	public Scacchi(Gioco gioco) {
		super("Progetto Scacchi: Cristina Giacomazzi - Christopher Petito");
		dash = gioco;
			
		// Imposto la grafica
		
		this.setLayout(new GridLayout(8, 8));
		setResizable(false);
		showBoard(-1,-1);
		setSize(640, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Centro la finestra
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}
	
	// Metodo che genera i bottoni e quindi la grafica che assumeranno gli scacchi.
	private void showBoard(int xIniziale, int yIniziale) {	
		// Controlla se il primo click corrisponde a una pedina del giocatore attuale
		boolean trovata=false;

		//inizio scansione scacchiera
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				
				//Vuol dire che ho chiamato lo showBoard con l'intento di visuallizare le mosse suggerite
				
				if (xIniziale != -1) {
					trovata=false;
					//lista delle mosse della casella (xiniziale yinizliale)
					LinkedList<Mossa> mossePedina = dash.suggerisciMosse(new controller.Casella(xIniziale, yIniziale));
					
					// Controllo se la mossa � fattibile e memorizzo l'ultima casella raggiunta in casella
					
					for (int i = 0; i < mossePedina.size(); i++) {
						Mossa pop = mossePedina.get(i);
						
							controller.Casella casella =  (controller.Casella) pop.caselleToccate.getLast();
						// Se stiamo iterando una delle caselle appartenenti a caselle toccate di pop
						if (casella.riga == y && casella.colonna == x) {
							trovata = true;
						}		
					}
				}
				
				final Casella p;
				//se la casella x,y corrisponde a una casella suggerita verr� colorata altrimenti
				//gli viene assegnata la pedina corrispondente
				
				if(trovata){
					p = generaPedina(dash.contenuto(y, x), 0, 0, true);			
				}
				else{
					p = generaPedina(dash.contenuto(y, x), x, y, false);
				}
						
				p.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//primo click, se ho cliccato su una pedina del player
						if ((dash.colore(dash.contenuto(p.getPosizione().x,p.getPosizione().y)) == BIANCO) && (dash.aChiTocca== BIANCO)) {
							click1 = p.getPosizione();
							System.out.println("primo click: " + click1);
							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							//passo alla showBoard il primo click
							showBoard(p.getPosizione().x,p.getPosizione().y);
							invalidate();
							validate();
						} 
						else if ((dash.colore(dash.contenuto(p.getPosizione().x,p.getPosizione().y)) == NERO)&& (dash.aChiTocca == NERO))
					{
							click1 = p.getPosizione();
							System.out.println("primo click: " + click1);
							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							//passo alla showBoard il primo click
							showBoard(p.getPosizione().x,p.getPosizione().y);
							invalidate();
							validate();
					}
						else if (click1 != null) {
							//Secondo click
							click2 = p.getPosizione();
							System.out.println("secondo click: " + click2);
							String messaggio = dash.provaMossaGiocatore(click1.x, click1.y, click2.x, click2.y);
		
							// Resetto i click
						
							click1 = null;
							click2 = null;

							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							showBoard(-1,-1);
							invalidate();
							validate();

							 if (messaggio == "Gioco finito!"){
								 stampaMessaggio(messaggio);
								 dispose();
								JFrame window = new SchermataFinale();
								window.setVisible(true);
							}else if (messaggio != null)
								stampaMessaggio(messaggio);
							
							
						} //Fine secondo click
					}
				});

				// Aggiungo il bottone
				add(p);
			}
		}
	}

	public Casella generaPedina(int valore, int x, int y, boolean colore) {
		// Genero le pedine
		if (colore == false)
			switch (valore) {
				case 10:
				case 11:
					if (dash.controlloCasellaNera(x, y)) 
						return new Pedone(valore,Color.white);
					return new Pedone(valore,Color.gray);
				case 21:
				case 20:
					if (dash.controlloCasellaNera(x, y)) 
						return new Torre(valore,Color.white);
					return new Torre(valore,Color.gray);
				case 31:
				case 30:
					if (dash.controlloCasellaNera(x, y)) 
						return new Cavallo(valore,Color.white);
					return new Cavallo(valore,Color.gray);
				case 41:
				case 40:
					if (dash.controlloCasellaNera(x, y)) 
						return new Alfiere(valore,Color.white);
					return new Alfiere(valore,Color.gray);
				case 51:
				case 50:
					if (dash.controlloCasellaNera(x, y)) 
						return new Re(valore,Color.white);
					return new Re(valore,Color.gray);
				case 61:
				case 60:
					if (dash.controlloCasellaNera(x, y)) 
						return new Regina(valore,Color.white);
					return new Regina(valore,Color.gray);
				case 0:
					if (dash.controlloCasellaNera(x, y)) 
						return new Vuota(Color.white);
					return new Vuota();
				default:
					return new Re(valore,Color.pink);
			}
		else if(colore == true){
			switch (valore) {
			case 10:
			case 11:
				Pedone p1 = new Pedone(valore,Color.blue);
				p1.setBorder(new LineBorder(Color.YELLOW, 4));
				return p1;
			case 21:
			case 20:
				 Torre p2 = new Torre(valore,Color.blue);
				 p2.setBorder(new LineBorder(Color.YELLOW, 4));
				 return p2;
			case 31:
			case 30:
				Cavallo p3 = new Cavallo(valore,Color.blue);
				p3.setBorder(new LineBorder(Color.YELLOW, 4));
				return p3;
			case 41:
			case 40:
				Alfiere p4 = new Alfiere(valore,Color.blue);
				p4.setBorder(new LineBorder(Color.YELLOW, 4));
				return p4;
			case 51:
			case 50:
				Re p5 = new Re(valore,Color.blue);
				p5.setBorder(new LineBorder(Color.YELLOW, 4));
				return p5;
			case 61:
			case 60:
				Regina p6 = new Regina(valore,Color.blue);
				p6.setBorder(new LineBorder(Color.YELLOW, 4));
				return p6;
			case 0:
				Vuota p7 = new Vuota(Color.blue);
				p7.setBorder(new LineBorder(Color.YELLOW, 4));
				return p7;
			default:
				Re p8 = new Re(valore,Color.pink);
				p8.setBorder(new LineBorder(Color.YELLOW, 4));
				return p8;
			}
		
		}
		// Penso a colorare la parte centrale
		if (y == 3)
			if (x % 2 == 1)
				return new Vuota(Color.white);
		// Penso a colorare la parte centrale
		if (y == 4)
			if (x % 2 == 0)
				return new Vuota(Color.white);

		// Di default tutto � bianco
		return new Vuota();
	}
	
	//Metodo che si preoccupa di stampare il messaggio passatogli per parametro.
	public static void stampaMessaggio(String s) {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, s);
	}
	
	//Metodo voi che avvia il gioco
	public static void avviaGioco(){
		new Scacchi(new Gioco()).setVisible(true);
	}

	public static void main(String[] args) {
		
		//Lancio la schermata iniziale
		JFrame window = new SchermataIniziale();
		window.setVisible(true);

		
	}

}