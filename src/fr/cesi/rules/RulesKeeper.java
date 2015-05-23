package fr.cesi.rules;

import fr.cesi.application.CacheConfig;
import fr.cesi.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class RulesKeeper.
 */
public class RulesKeeper {

	/** The cache config. */
	private CacheConfig cacheConfig = CacheConfig.getInstance();
	
	/** The mod. */
	private Difficulty mod;
	
	/** The round duration. */
	private long roundDuration;
	
	/** The lifes. */
	private int lifes;
	
	/** The speed. */
	private Double speed;
	
	/** The game is over. */
	private boolean gameIsOver = false;
	
	/** The is alive. */
	private boolean isAlive = true;
	
	/** The instance. */
	public static RulesKeeper instance = new RulesKeeper();
	
	/**
	 * Gets the single instance of RulesKeeper.
	 *
	 * @return single instance of RulesKeeper
	 */
	public static RulesKeeper getInstance() {
		return instance;
	}
	
	/**
	 * Instantiates a new rules keeper.
	 */
	private RulesKeeper() {
		this.mod = Difficulty.getMod(cacheConfig.getDifficulty());
		initialize();
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		this.roundDuration = cacheConfig.getRoundDuration(mod.getId()) * 1000; // millis to seconds
		this.lifes = cacheConfig.getLifesNumber(); 
		this.speed = cacheConfig.getSpeed(mod.getId());
		this.gameIsOver = false;
	}
	
	/** The begin. */
	private long begin;
	
	/**
	 * Sets the begin.
	 */
	private void setBegin() {
		begin = System.currentTimeMillis();
	}
	
	/**
	 * Start timer.
	 */
	public void startTimer() {
		setBegin();
	}
	
	/** The timer. */
	private long timer;
	
	/**
	 * Update timer.
	 */
	private void updateTimer() {
		timer = System.currentTimeMillis() - begin;
	}
	
	/**
	 * Check timer.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Update lifes.
	 */
	public void updateLifes() {
		lifes--;
		if(lifes <= 0) {
			gameIsOver = true;
		}
	}
	
	/**
	 * Gets the lifes.
	 *
	 * @return the lifes
	 */
	public int getLifes() {
		return lifes;
	}
	
	/**
	 * Gets the game is over.
	 *
	 * @return the game is over
	 */
	public boolean getGameIsOver() {
		return gameIsOver;
	}
	
	/**
	 * Sets the is alive.
	 */
	public void setIsAlive() {
		isAlive = !isAlive;
	}
	
	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/**
	 * New round.
	 */
	public void newRound() {
		setIsAlive();
		updateLifes();
	}
	
	/**
	 * The Enum ScoreType.
	 */
	public enum ScoreType {
		
		/** The line. */
		LINE,
		
		/** The round. */
		ROUND;
	}
	
	/** The score. */
	private int score = 0;
	
	/**
	 * Update score.
	 *
	 * @param scoreType the score type
	 */
	private int roundScore = 0;
	public void updateScore(ScoreType scoreType) {
		switch(scoreType) {
		case LINE:
			roundScore += Utils.tryParseToInt(cacheConfig.getProperty("ligne.score"));
			break;
		case ROUND:
			score += roundScore * getTimeToEnd() / 1000;
			roundScore = 0;
			break;
		}
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * The Enum Difficulty.
	 */
	public enum Difficulty {
		
		/** The easy. */
		EASY(0),
		
		/** The medium. */
		MEDIUM(1),
		
		/** The hard. */
		HARD(2);
		
		/** The id. */
		private int id;
		
		/**
		 * Instantiates a new difficulty.
		 *
		 * @param difficulty the difficulty
		 */
		Difficulty(int difficulty) {
			this.id = difficulty;
		}
		
		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		
		/**
		 * Gets the mod.
		 *
		 * @param id the id
		 * @return the mod
		 */
		public static Difficulty getMod(int id) {
			for(Difficulty i : Difficulty.values()) {
				if(i.getId() == id) {
					return i;
				}
			}
			return null;
		}
	}
	
	/**
	 * Gets the mod.
	 *
	 * @return the mod
	 */
	public Difficulty getMod() {
		return mod;
	}

	/**
	 * Sets the mod.
	 *
	 * @param mod the new mod
	 */
	public void setMod(Difficulty mod) {
		this.mod = mod;
	}
	
	/**
	 * Change mod.
	 *
	 * @param id the id
	 */
	public void changeMod(int id) {
		setMod(Difficulty.getMod(id));
		updateRules();
	}

	/**
	 * Update rules.
	 */
	private void updateRules() {
		initialize();
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

}
