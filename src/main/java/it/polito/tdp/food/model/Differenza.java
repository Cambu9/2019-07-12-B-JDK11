package it.polito.tdp.food.model;

import java.util.Objects;

public class Differenza {

	private Food f;
	private Double differenza;
	
	public Differenza(Food f, Double differenza) {
		super();
		this.f = f;
		this.differenza = differenza;
	}

	public Food getF() {
		return f;
	}

	public void setF(Food f) {
		this.f = f;
	}

	public Double getDifferenza() {
		return differenza;
	}

	public void setDifferenza(Double differenza) {
		this.differenza = differenza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(differenza, f);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Differenza other = (Differenza) obj;
		return Objects.equals(differenza, other.differenza) && Objects.equals(f, other.f);
	}

	@Override
	public String toString() {
		return "Differenza [f=" + f + ", differenza=" + differenza + "]";
	}
	
	
}
