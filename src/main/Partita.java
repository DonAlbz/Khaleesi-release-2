package main;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import inputDati.InputDati;
import inputDati.MyMenu;

public class Partita {
	private Map<Point, AbstractObject> casella = new HashMap<>();
	private Guerriero guerriero;
	private boolean continuare = true;
	private String RICHIESTA_CONTINUAZIONE = "Vuoi continuare la partita?";
	private String RICHIESTA_SPOSTAMENTO = "Seleziona una direzione di movimento del guerriero";
	private String[] SPOSTAMENTI_POSSIBILI={"NORD", "SUD", "OVEST", "EST", "TORNA IN CITTA'"};
	private MyMenu menuSpostamento = new MyMenu(RICHIESTA_SPOSTAMENTO, SPOSTAMENTI_POSSIBILI);
	private String ERRORE_SPOSTAMENTO = "Errore: il guerriero non � stato spostato nella direzione indicata";
	
	public Partita() {
		creaCasella(new Point(0,0), null);
		guerriero = new Guerriero();		
	}
	
	public void start() {
		while (continuare){
			richiediSpostamento();
		}
	}


	private void richiediSpostamento() {
		assert guerriero.getPosizione()!=null;
		Point posizioneVecchia= new Point(guerriero.getPosizione());
		switch(menuSpostamento.scegli()){
		case 0: 
			continuare=false;
			break;
		case 1:
			eseguiSpostamento(0, 1);
			assert posizioneVecchia.getY()+1==guerriero.getPosizione().getY() : ERRORE_SPOSTAMENTO ;
			break;
		case 2:
			eseguiSpostamento(0, -1);
			assert posizioneVecchia.getY()-1==guerriero.getPosizione().getY() : ERRORE_SPOSTAMENTO ;
			break;
		case 3:
			eseguiSpostamento(-1, 0);
			assert posizioneVecchia.getX()-1==guerriero.getPosizione().getX() : ERRORE_SPOSTAMENTO ;
			break;
		case 4:
			eseguiSpostamento(1, 0);
			assert posizioneVecchia.getX()+1==guerriero.getPosizione().getX() : ERRORE_SPOSTAMENTO ;
			break;
		case 5:
			guerriero.getPosizione().setLocation(0, 0);
			assert (guerriero.getPosizione().getX() == 0 && guerriero.getPosizione().getY()==0) : ERRORE_SPOSTAMENTO ;
			break;
		default:
			assert false;
		}
		
	}
	
	/**Permette di effettuare uno spostamento differenziale dalla posizione in cui si trova il guerriero
	 * @param x spostamento intero dx
	 * @param y spostamento intero dy
	 */
	private void eseguiSpostamento(int x, int y) {
		guerriero.getPosizione().translate(x, y);
		if(!esisteCasella(guerriero.getPosizione())){
			Point newPosizione = new Point();
			newPosizione.setLocation(guerriero.getPosizione());
			creaCasella(newPosizione,null);
		}		
	}

	private void creaCasella(Point coordinate, AbstractObject abstractObject){
		casella.put(coordinate, abstractObject);	
	}

	private Boolean esisteCasella(Point coordinate){
		return casella.containsKey(coordinate);
	}

	private AbstractObject contenutoCasella(Point coordinate){
		return casella.get(coordinate);

	}

}