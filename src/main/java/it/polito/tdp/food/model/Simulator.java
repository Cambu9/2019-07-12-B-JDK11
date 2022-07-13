package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {

	Model m;
	
	//input
	private int K;
	
	//output
	public List<Food> cibiPreparati;
	public Double minuti;
	
	//grafo
	Graph<Food, DefaultWeightedEdge> grafo;
	
	//list dei cibi da prendere
	List<Food> vicini;
	
	//coda
	PriorityQueue<Event> queue;
	
	public Simulator(Model m, int K) {
		this.K = K;
		this.m = m;
	}
	
	public void init(List<Differenza> diff) {
		this.minuti = 0.0;
		this.cibiPreparati = new ArrayList<Food>();
		
		//creo la coda
		queue = new PriorityQueue<>();
		int i = 0;
//		List<Differenza> diff = new ArrayList<>();
//		diff = m.differenzaMinima(current);
		for(Differenza f: diff) {
			if(i<K && (f!=null)) {
				queue.add(new Event(f.getDifferenza()*60, i, f.getF()));
				i++;
				this.cibiPreparati.add(f.getF());		
			}
		}
	}
	
	public void run() {
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			this.minuti = e.getTime();
			processEvent(e);
		}
	}
	public void processEvent(Event e) {
		Double time = e.getTime();
		int stazione = e.getStazione();
		Food f = e.getF();
		int i = 0;
		List<Differenza> vicini = new ArrayList<>();
		vicini = m.differenzaMinima(f);
		if(vicini.isEmpty())
			return;
		for(Differenza d: vicini) {
			if(!this.cibiPreparati.contains(d.getF())){
				if(i<1 && (d!=null)) {
					queue.add(new Event(time+(d.getDifferenza()*60), stazione, d.getF()));
					i++;
					this.cibiPreparati.add(d.getF());
				}				
			}
		}
	}
}
