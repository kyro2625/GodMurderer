import java.util.StringTokenizer;

public class Edge implements Comparable {
    Vertex u;       //đỉnh nguồn
    Vertex v;       //đỉnh đích
    double weight;  //trọng số
    public Edge(Vertex u, Vertex v) {
        this.u = this.v = v; weight = 1;
    }

    public Edge(Vertex u, Vertex v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public Vertex getU() {
        return u;
    }

    public void setU(Vertex u) {
        this.u = u;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int compareTo(Object o ) {
        Edge e = ((Edge)o);
        return (u.name + v.name).compareTo(e.u.name + e.v.name);
    }

    @Override
    public String toString() {
        return "{" +
                  u.name +
                ", " + v.name +
                ", " + weight +
                '}';
    }
}
