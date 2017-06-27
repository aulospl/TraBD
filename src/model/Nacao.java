package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacao {
	private final StringProperty nome;
	private final StringProperty continente;
	private final IntegerProperty numAtletas;
	private final StringProperty modalidade;
	private final StringProperty hino;
	private final StringProperty bandeira;
	
	public Nacao(String nome, String continente, int num, String modalidade, String hino, String bandeira) {
		this.nome = new SimpleStringProperty(nome);
		this.continente = new SimpleStringProperty(continente);
		this.numAtletas = new SimpleIntegerProperty(num);
		this.modalidade = new SimpleStringProperty(modalidade);
		this.hino = new SimpleStringProperty(hino);
		this.bandeira = new SimpleStringProperty(bandeira);
	}
	
	
	
	public StringProperty getNome() {
		return nome;
	}
	
	public StringProperty getContinente() {
		return continente;
	}
	
	public IntegerProperty getNumAtletas() {
		return numAtletas;
	}
	
	public StringProperty getModalidade() {
		return modalidade;
	}
	
	public StringProperty getHino() {
		return hino;
	}
	
	public StringProperty getBandeira() {
		return bandeira;
	}
}
