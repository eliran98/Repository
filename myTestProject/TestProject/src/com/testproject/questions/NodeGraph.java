package com.testproject.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodeGraph {
	
	private List<NodeGraph> adjacent;
	private State state;
	
	private enum State {
		Unvisited, Visited, Visiting;
	}
	
	public NodeGraph(){
		this.state = State.Unvisited;
		adjacent = new ArrayList<NodeGraph>();
	}
	
	public List<NodeGraph> getAdjacent(){
		return adjacent;
	}
	
	/**
	 * 4.2
	 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
	 * @param graph
	 * @param start
	 * @param end
	 * @return
	 */
	public static NodeGraph searchPathBetweenStartToEnd(Graph graph ,NodeGraph start ,NodeGraph end){
		List<NodeGraph> listAllNodes = graph.getNodes();
		for (NodeGraph nodeGraph : listAllNodes) {
			nodeGraph.state = State.Unvisited;
		}
		
		LinkedList<NodeGraph> stack = new LinkedList<NodeGraph>();
		stack.add(start);
		
		while(!stack.isEmpty()){
			
			NodeGraph u = stack.removeFirst();
			if(u != null){
				for (int i = 0 ; i < u.getAdjacent().size() ; i++) {
					NodeGraph v = u.getAdjacent().get(i);
					if(v.state == State.Unvisited){
						if(v == end){
							return end;
						}else{
							v.state = State.Visiting;
							stack.add(v);
						}
					}
				}
				
				u.state = State.Visited;
			}
		
		}
		
		return null;
	}
}
