package main;

import java.awt.Point;

import inputDati.InputDati;

public class Guerriero {
	private String nome;
	private Point posizione;
	private int puntiVita;
	private int monete;
	private Arma arma;
	
	public Guerriero(){
		this.nome=richiestaNome();
		puntiVita=Parametri.PUNTI_VITA_INIZIALI;
		posizione=new Point(0,0);
	}
	
	public Point getPosizione(){
		return posizione;
	}
	
	private String richiestaNome() {
		return Visualizzatore.richiestaNomeGuerriero();
	}
	
	public void impugna(Arma arma){
		assert this.arma == null;
		this.arma=arma;
	}

	
	
	/**
	 * @return the arma
	 */
	public Arma getArma() {
		return arma;
	}

	/**
	 * @param monete the monete to set
	 */
	public void setMonete(int monete) {
		this.monete = monete;
	}

	/**
	 * @return the puntiVita
	 */
	public int getPuntiVita() {
		return puntiVita;
	}

	public void subisciDanni(int danni){
		puntiVita-=danni;
	}
		
	public void riceviCura(int cura){
		puntiVita+=cura;
	}

	public void dropArma() {
		this.arma=null;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	
	
}
