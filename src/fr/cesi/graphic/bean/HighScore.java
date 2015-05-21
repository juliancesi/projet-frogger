package fr.cesi.graphic.bean;

import java.io.Serializable;

public class HighScore implements Serializable {
	
	private String pseudo = "";
	private long score = 0;
	
	public HighScore(String pseudo, long score) {
		this.pseudo = pseudo;
		this.score = score;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	
}
