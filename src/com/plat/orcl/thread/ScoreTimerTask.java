package com.plat.orcl.thread;

import java.util.TimerTask;

import com.plat.orcl.domain.Person;

public abstract class ScoreTimerTask extends TimerTask {
	
	private Person p;
	ScoreTimerTask(Person p){
		this.p=p;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	

}
