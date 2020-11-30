package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DWGraph_Algo implements dw_graph_algorithms {

    ////private variables////

    private directed_weighted_graph graph;
    private int mc;

    /////////////////////////

    @Override
    public void init(directed_weighted_graph g) {
        if(g != null)
            graph = g;
    }

    @Override
    public directed_weighted_graph getGraph() {
        return graph;
    }

    @Override
    public directed_weighted_graph copy() {
        return new DWGraph_DS(graph);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    /**
     * sets the tag of all nodes in the graph to the given value: val.
     *
     * @param val
     */
    private void resetTag(int val) {
        for (node_data index : graph.getV()) {
            index.setTag(val);
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (this.graph.getNode(src) != null && this.graph.getNode(dest) != null) {
            if (src == dest) {
                return 0;
            }
            this.mc = this.graph.getMC();
            resetTag(-1);
            graph.getNode(src).setTag(0);
            Queue<node_data> q = new LinkedList<node_data>();
            q.add(this.graph.getNode(src));
            while (!q.isEmpty()) {
                node_data temp = q.poll();
                double length = temp.getTag();
                for (edge_data i : this.graph.getE(temp.getKey())) {
                    double length_i = i.getWeight();
                    if (i.getTag() < 0 || i.getTag() > length + length_i) {
                        i.setTag((int) (length + length_i));
                        q.add(graph.getNode(i.getDest()));
                    }
                }
            }
        }
        return this.graph.getNode(dest).getTag();
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {

        return null;
    }

    @Override
    public boolean save(String file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("{\"Edges\":[");
            for (node_data n : this.graph.getV()) {
                for (edge_data n2 : this.graph.getE(n.getKey())) {
                    int key1 = n.getKey(), key2 = n2.getDest();
                    Double w = this.graph.getEdge(key1, key2).getWeight();
                    sb.append("{\"src\":");
                    String str = key1+",\"w\""+key2+","+w+"\n";
                    sb.append(str);
                    pw.write(sb.toString());
                    sb.setLength(0);
                }
            }
            sb.append("],\"Nodes\":[");
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            DWGraph_DS g = new DWGraph_DS();
            boolean flag = true;
            if (br != null) {
                String s = br.readLine();
                while (s != null && flag) {
                    String[] arr = s.split(",");
                    int x1 = Integer.parseInt(arr[1]);
                    node_data n1 = new NodeData(x1);
                    g.addNode(n1);
                    int x2 = Integer.parseInt(arr[2]);
                    node_data n2 = new NodeData(x2);
                    g.addNode(n2);
                    double d = Double.parseDouble(arr[3]);
                    g.connect(x1, x2, d);
                    if (br != null) {
                        s = br.readLine();
                    } else flag = false;
                }
            }
            if (g != null) {
                this.graph = g;

                return true;
            }
            return false;
        } catch (IOException x) {
            return false;
        }
    }
}
