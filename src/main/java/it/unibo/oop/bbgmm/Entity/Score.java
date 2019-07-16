package it.unibo.oop.bbgmm.Entity;

import java.io.Serializable;

public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044857723462203092L;
	private final String playerName;
	private final int level;
	
	public Score(String playerName, int level) {
		this.playerName=playerName;
		this.level=level;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getLevel() {
		return level;
	}
}