package api;

public class EdgeData implements edge_data{

    ////private variables////

    private int src, dest, tag;
    private double weight;
    private String info;

    /////////////////////////

    public EdgeData(int src, int dest, double weight){
        this.tag = 0;
        this.info = "";
        this.dest = dest;
        this.src = src;
        this.weight = weight;
    }
    public EdgeData(EdgeData edge){
        this.tag = edge.getTag();
        this.info = edge.getInfo();
        this.dest = edge.getDest();
        this.src = edge.getSrc();
        this.weight = edge.getWeight();
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;

    }
}
