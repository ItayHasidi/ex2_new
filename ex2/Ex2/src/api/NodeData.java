package api;

public class NodeData implements node_data {

    private class location implements geo_location{

        private double x, y, z, dis;

        public location(double x, double y, double z, double dis){
            this.x = x;
            this.y = y;
            this.z = z;
            this.dis = dis;
        }

        public location(geo_location loc){
            this.x = loc.x();
            this.y = loc.y();
            this.z = loc.z();
            this.dis = distance(loc);
        }

        @Override
        public double x() {
            return x;
        }

        @Override
        public double y() {
            return y;
        }

        @Override
        public double z() {
            return z;
        }

        @Override
        public double distance(geo_location g) {
            double pow_x = this.x - g.x();
            pow_x = Math.pow(pow_x, 2);
            double pow_y = this.y - g.y();
            pow_y = Math.pow(pow_y, 2);
            double pow_z = this.z - g.z();
            pow_z = Math.pow(pow_z, 2);
            double res = pow_x + pow_y + pow_z;
            res = Math.sqrt(res);
            return res;
        }
    }

    ////private variables////
    private static int keyCount = 0;
    private int key, tag;
    private geo_location loc;
    private double weight;
    private String info;

    /////////////////////////

    public NodeData(int key){
        this.key = key;
        this.tag = 0;
        this.loc = new location(loc);
        this.weight = weight;
        this.info = "";
    }

    public NodeData(geo_location loc, double weight){
        this.key = keyCount;
        keyCount++;
        this.tag = 0;
        this.loc = new location(loc);
        this.weight = weight;
        this.info = "";
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public geo_location getLocation() {
        return this.loc;
    }

    @Override
    public void setLocation(geo_location p) {
//        this.loc = new location(p);
        this.loc = p;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
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
