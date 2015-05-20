package fr.cesi.rules;

import fr.cesi.application.CacheConfig;

public class RulesKeeper {

	private CacheConfig cacheConfig = CacheConfig.getInstance();
	
	private long round;
	private int lifes;
	private boolean gameIsOver = false;
	
	private boolean isAlive = true;
	
	private static RulesKeeper instance = new RulesKeeper(); 
	public static RulesKeeper getInstance() {
		return instance;
	}
	
	private RulesKeeper() {
		this.round = cacheConfig.getRoundDuration() * 1000; // millis to seconds
		this.lifes = cacheConfig.getLifesNumber(); 
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
		
		if(timer >= round) {
			return false;
		}
		return true;
	}
	
	public long getTimeToEnd() {
		return (round - timer) / 1000;
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
		LIGNE(10),
		ROUND(50);
		
		private int points;
		ScoreType(int points) {
			this.points = points;
		}
		public int getPoints() {
			return points;
		}
	}
	private int score = 0;
	public void updateScore(ScoreType scoreType) {
		score += scoreType.getPoints();
	}
	
	public int getScore() {
		return score;
	}
}
