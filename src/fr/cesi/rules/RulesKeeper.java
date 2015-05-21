package fr.cesi.rules;

import fr.cesi.application.CacheConfig;
import fr.cesi.util.Utils;

public class RulesKeeper {

	private CacheConfig cacheConfig = CacheConfig.getInstance();
	
	private Difficulty mod;
	private long roundDuration;
	private int lifes;
	private Double speed;
	private boolean gameIsOver = false;
	private boolean isAlive = true;
	
	public static RulesKeeper instance = new RulesKeeper();
	public static RulesKeeper getInstance() {
		return instance;
	}
	
	private RulesKeeper() {
		this.mod = Difficulty.getMod(cacheConfig.getDifficulty());
		this.roundDuration = cacheConfig.getRoundDuration(mod.getId()) * 1000; // millis to seconds
		this.lifes = cacheConfig.getLifesNumber(); 
		this.speed = cacheConfig.getSpeed(mod.getId());
	}
	
	private long begin;
	private void setBegin() {
		begin = System.currentTimeMillis();
	}
	
	public void startTimer() {
		setBegin();
	}
	
	private long timer;
	private void updateTimer() {
		timer = System.currentTimeMillis() - begin;
	}
	
	public boolean checkTimer() {
		updateTimer();
		
		if(timer >= roundDuration) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the round duration.
	 *
	 * @return the round
	 */
	public long getRound() {
		return roundDuration;
	}
	
	/**
	 * Gets the time to end.
	 *
	 * @return the time to end, long, return the time in rest before end in milliseconds.
	 */
	public long getTimeToEnd() {
		return roundDuration - timer;
	}
	
	public void updateLifes() {
		lifes--;
		if(lifes <= 0) {
			gameIsOver = true;
		}
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public boolean getGameIsOver() {
		return gameIsOver;
	}
	
	public void setIsAlive() {
		isAlive = !isAlive;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void newRound() {
		setIsAlive();
		updateLifes();
	}
	
	public enum ScoreType {
		LINE,
		ROUND;
	}
	
	private int score = 0;
	public void updateScore(ScoreType scoreType) {
		switch(scoreType) {
		case LINE:
			score += Utils.tryParseToInt(cacheConfig.getProperty("ligne.score"));
			break;
		case ROUND:
			score *= getTimeToEnd() / 1000;
			break;
		}
	}
	
	public int getScore() {
		return score;
	}

	public enum Difficulty {
		EASY(0),
		MEDIUM(1),
		HARD(2);
		
		private int id;
		Difficulty(int difficulty) {
			this.id = difficulty;
		}
		public int getId() {
			return id;
		}
		public static Difficulty getMod(int id) {
			for(Difficulty i : Difficulty.values()) {
				if(i.getId() == id) {
					return i;
				}
			}
			return null;
		}
	}
	
	public Difficulty getMod() {
		return mod;
	}

	public void setMod(Difficulty mod) {
		this.mod = mod;
	}
	
	public void changeMod(Difficulty mod) {
		setMod(mod);
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

}
