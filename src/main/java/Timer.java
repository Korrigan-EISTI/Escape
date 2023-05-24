package main.java;

public class Timer {
	
	private double seconds;
	private boolean finished;
	
	public Timer(int ms) {
		this.setSeconds(ms*60);
		this.setFinished(false);
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	@Override
	public String toString() {
		return "Temps restant : "+String.valueOf((int) (this.getSeconds()/60))+" secondes.";
	}

	/**
     * Met a jour le timer.
     */
    public void tick() {
        if(this.seconds <= 0) this.setFinished(true);
        else this.setSeconds(this.getSeconds()-1);
    }
}
