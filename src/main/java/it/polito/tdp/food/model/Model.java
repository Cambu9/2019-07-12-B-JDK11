package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private Graph<Food, DefaultWeightedEdge> grafo;
	FoodDao dao;
	private Simulator sim;
	
	public Model() {
		dao = new FoodDao();
	}
	
	public String creaGrafo(int n) {
		//creo il grafo
		grafo = new SimpleDirectedWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//aggiungo i vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici(n));
		
		//aggiungo gli archi
		List<Food> vertici = new ArrayList<Food>(this.grafo.vertexSet());
		for(Food f1: vertici) {
			for(Food f2: vertici) {
				if(f1.getFood_code()>(f2.getFood_code())) {
					Double peso = f1.getPeso()-f2.getPeso();
					if(peso>0.0) {
						Graphs.addEdge(this.grafo, f1, f2, peso);
					}else if(peso<0.0){
						Graphs.addEdge(this.grafo, f2, f1, -peso);
					}
				}
			}
		}
		return "Grafo Creato \n#Vertici: " + this.grafo.vertexSet().size() + "\n#Archi: " + this.grafo.edgeSet().size() + "\n";
	}
	public List<Food> getVertici(){
		List<Food> lista = new ArrayList<Food>(this.grafo.vertexSet());
		return lista;
	}
	

	
	public List<Differenza> differenzaMinima(Food f) {
		List<Differenza> list = new ArrayList<Differenza>();
		List<Food> vicini = new ArrayList<Food>();
		vicini = Graphs.successorListOf(this.grafo, f);
		for(Food f1: vicini) {
			Double differenza = Math.abs(this.grafo.getEdgeWeight(this.grafo.getEdge(f, f1)));
			list.add(new Differenza(f1, differenza));
		}
		Collections.sort(list, new Comparator<Differenza>(){

			@Override
			public int compare(Differenza o1, Differenza o2) {
				// TODO Auto-generated method stub
				return o1.getDifferenza().compareTo(o2.getDifferenza());
			}
			
		});
		return list;
	}
	
	public Simulator Simulatore(int k) {
		return sim = new Simulator(this, k);
	}
}
