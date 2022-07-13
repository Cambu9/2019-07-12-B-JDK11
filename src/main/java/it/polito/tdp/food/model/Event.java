package it.polito.tdp.food.model;

import java.util.Objects;

public class Event implements Comparable{
	
	private Double time;
	private int stazione;
	private Food f;
	
	public Event(Double time, int stazione, Food f) {
		super();
		this.time = time;
		this.stazione = stazione;
		this.f = f;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public int getStazione() {
		return stazione;
	}
	public void setStazione(int stazione) {
		this.stazione = stazione;
	}
	public Food getF() {
		return f;
	}
	public void setF(Food f) {
		this.f = f;
	}
	@Override
	public int hashCode() {
		return Objects.hash(f, stazione, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(f, other.f) && stazione == other.stazione && Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", stazione=" + stazione + ", f=" + f + "]";
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
