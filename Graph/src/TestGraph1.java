import java.util.ArrayList;

public class TestGraph1 {
    public static void main(String[] args) {
        Graph g = new Graph();
        g = g.loadFromFile1("D:\\Study At FPTU\\3. Fall 2020\\CSD201 - Data Structures and Algorithms - DuongVTT\\Testing Place\\Graph\\graph1.txt");
        System.out.println(g.toString());
        ArrayList<Edge> edges = g.depthFirstSearch();
        System.out.println("Depth First Search");
        g.printEdges(edges,System.out);
        edges = g.breadthFirstSearch();
        System.out.println("Breadth First Search");
        g.printEdges(edges,System.out);
    }
}
