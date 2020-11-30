package api;

import java.util.Collection;
import java.util.HashMap;

public class DWGraph_DS implements directed_weighted_graph{

    ////private variables////

    private static HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
    private static HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>(); // key: <src,dest>
    private static int countMC = 0, countEdge = 0;

    /////////////////////////

    public DWGraph_DS(){}

    public DWGraph_DS(directed_weighted_graph graph){
        for(node_data temp: graph.getV()){
            addNode(temp);
        }
        for(node_data temp: graph.getV()){
            for(edge_data temp2: graph.getE(temp.getKey())){
                connect(temp.getKey(), temp2.getDest(), temp2.getWeight());
            }
        }
    }


    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        return edges.get(src).get(dest);
    }

    @Override
    public void addNode(node_data n) {
        if(n != null) {
            nodes.putIfAbsent(n.getKey(), n);
            HashMap<Integer, edge_data> de = new HashMap<Integer, edge_data>();
            edges.putIfAbsent(n.getKey(), de);
            countMC++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(nodes.containsKey(src) && nodes.containsKey(dest) && src != dest) {
            EdgeData ed = new EdgeData(src, dest, w);
            edges.get(src).putIfAbsent(dest, ed);
            countEdge++;
            countMC++;
        }
    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        return edges.get(node_id).values();
    }

    @Override
    public node_data removeNode(int key) {
        if(nodes.containsKey(key)) {
            edges.remove(key);
            for (node_data temp : getV()) {
                if(edges.get(temp.getKey()).containsKey(key))
                    edges.get(temp.getKey()).remove(key);
                countMC++;
                countEdge--;
            }
            node_data res = nodes.get(key);
            nodes.remove(key);
            countMC++;
            return res;
        }
        return null;
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(nodes.containsKey(src) && nodes.containsKey(dest) && src != dest && edges.get(src).containsKey(dest)) {
            edges.get(src).remove(dest);
            countMC++;
            countEdge--;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return countEdge;
    }

    @Override
    public int getMC() {
        return countMC;
    }
}
