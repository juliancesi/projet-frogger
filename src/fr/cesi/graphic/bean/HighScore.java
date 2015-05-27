package fr.cesi.graphic.bean;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class HighScore.
 */
public class HighScore implements Serializable {
	
	/** The pseudo. */
	private String pseudo = "";
	
	/** The score. */
	private long score = 0;
	
	/**
	 * Instantiates a new high score.
	 *
	 * @param pseudo the pseudo
	 * @param score the score
	 */
	public HighScore(String pseudo, long score) {
		this.pseudo = pseudo;
		this.score = score;
	}
	
	/**
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Sets the pseudo.
	 *
	 * @param pseudo the new pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public long getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(long score) {
		this.score = score;
	}
	
}
