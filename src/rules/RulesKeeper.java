package rules;

public class RulesKeeper {

	private long round;
	public RulesKeeper(long round) {
		this.round = round * 1000; // millis to seconds
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
}
