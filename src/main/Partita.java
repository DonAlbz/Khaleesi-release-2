package main;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import inputDati.InputDati;
import inputDati.MyMenu;

public class Partita {
	private Map<Point, AbstractObject> casella = new HashMap<>();
	private Guerriero guerriero;
	private Stato stato;
	private boolean continuare = true;
	private String RICHIESTA_CONTINUAZIONE = "Vuoi continuare la partita?";
	private String RICHIESTA_SPOSTAMENTO = "Seleziona una direzione di movimento del guerriero";
	private String[] SPOSTAMENTI_POSSIBILI={"NORD", "SUD", "OVEST", "EST", "TORNA IN CITTA'"};
	private MyMenu menuSpostamento = new MyMenu(RICHIESTA_SPOSTAMENTO, SPOSTAMENTI_POSSIBILI);
	private String ERRORE_SPOSTAMENTO = "Errore: il guerriero non è stato spostato nella direzione indicata";
	
	public Partita() {
		creaCasella(new Point(0,0), null); //inizializza la casella iniziale vuota
		guerriero = new Guerriero();	
		stato= new Disarmato(this);
	}
	
	public void start() {
		richiediSpostamento();
		while (continuare){	
			System.out.println(contenutoCasellaToString(guerriero.getPosizione()));
			stato.eseguiAzione();
			richiediSpostamento();
		}
		//System.out.println(casella);//Stampa la mappa
	}

	/**Permette all'utente di decidere la direzione di spostamento del {@link #guerriero}
	 * 
	 */
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
			creaCasella(newPosizione,ObjectFactory.creaContenutoCasella());
		}		
	}

	/**Serve sia a creare una casella, sia a modificare il contenuto di una casella gia' presente
	 * @param coordinate
	 * @param abstractObject
	 */
	private void creaCasella(Point coordinate, AbstractObject abstractObject){
		casella.put(coordinate, abstractObject);	
	}
	
	/**Ridondante, ma il metodo ha un nome piu' comprensibile dall'esterno
	 * @param coordinate
	 * @param abstractObject
	 */
	public void modificaCasella(Point coordinate, AbstractObject abstractObject){
		creaCasella(coordinate, abstractObject);
	}

	private Boolean esisteCasella(Point coordinate){
		return casella.containsKey(coordinate);
	}

	public Boolean isVuota(Point coordinate){
		return casella.get(coordinate)==null;
	}
	
	public AbstractObject contenutoCasella(Point coordinate){
		return casella.get(coordinate);
	}
	
	private String contenutoCasellaToString(Point coordinate){
		if(contenutoCasella(coordinate)!=null)
			return contenutoCasella(coordinate).getNomeOggetto();
		else
			return Visualizzatore.VUOTO;		
	}

	public void setStato(Stato stato){
		this.stato=stato;
	}
	/**
	 * @return the guerriero
	 */
	public Guerriero getGuerriero() {
		return guerriero;
	}
	
}