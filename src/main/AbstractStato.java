package main;

public abstract class AbstractStato implements Stato {
	Partita partita;
	Guerriero guerriero;
	
	public AbstractStato (Partita partita) {
		this.partita=partita;
		this.guerriero=partita.getGuerriero();
	}

	@Override
	public abstract void eseguiAzione();
	
	/**Chiede se utilizzare la pozione trovata, in caso affermativo la utilizza
	 * @param pozione
	 */
	public void trovataPozione(Pozione pozione){
		assert pozione!=null;
		Boolean scelta= Visualizzatore.scegliUtilizzoPozione(pozione.getNomeOggetto());
		if(scelta)
			usaPozione(pozione);
	}
	
	/**Applica la cura a {@link #guerriero}
	 * @param pozione
	 */
	private void usaPozione(Pozione pozione) {
		guerriero.riceviCura(pozione.getTotaleCura());
		partita.modificaCasella(guerriero.getPosizione(), null); // Elimina la pozione dalla casella
		Visualizzatore.notificaCura(guerriero.getNome(),pozione.getTotaleCura(), guerriero.getPuntiVita());
	}

	/**Chiede se aprire il forziere trovato, in caso affermativo lo apre
	 * @param forziere
	 */
	public void trovatoForziere(Forziere forziere){
		assert forziere!=null;
		Boolean scelta= Visualizzatore.scegliAperturaForziere(forziere.getNomeOggetto());
		if(scelta){
			apriForziere(forziere);
			assert forziere.getMonete()==0 && forziere.getVeleno()==0; //una volta aperto il forziere, esso deve essere vuoto
		}
	}
	
	/**Funzione che apre il forziere, ne scopre il contenuto e applica le conseguenze dell'apertura a {@link #guerriero}
	 * @param forziere aperto
	 */
	private void apriForziere(Forziere forziere) {
		assert forziere!=null;
		if(forziere.isVuoto()){
			System.out.println(Visualizzatore.FORZIERE_VUOTO);
		}
		if(forziere.getMonete()!=0){
			int monete=forziere.getMonete();
			guerriero.setMonete(monete);
			forziere.setMonete(0);
			System.out.printf(Visualizzatore.FORZIERE_TROVATE_MONETE,monete);
		}
		if(forziere.getVeleno()!=0){
			int veleno=forziere.getVeleno();
			guerriero.subisciDanni(veleno);
			forziere.setVeleno(0);
			System.out.printf(Visualizzatore.FORZIERE_TROVATO_VELENO,veleno);
		}
	}

}
