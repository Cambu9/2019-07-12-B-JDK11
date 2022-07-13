package it.polito.tdp.food.model;

import java.util.Objects;

public class Food {
	private Integer food_code;
	private String display_name;
	private Double peso;
	
	public Food(Integer food_code, String display_name, Double peso) {
		super();
		this.food_code = food_code;
		this.display_name = display_name;
		this.peso = peso;
	}
	
	
	public Double getPeso() {
		return peso;
	}


	public void setPeso(Double peso) {
		this.peso = peso;
	}


	public Integer getFood_code() {
		return food_code;
	}
	public void setFood_code(Integer food_code) {
		this.food_code = food_code;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(display_name, food_code, peso);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		return Objects.equals(display_name, other.display_name) && Objects.equals(food_code, other.food_code)
				&& Objects.equals(peso, other.peso);
	}


	@Override
	public String toString() {
		return display_name;
	}

	
	
}
