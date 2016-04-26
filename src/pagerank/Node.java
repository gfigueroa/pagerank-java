package pagerank;

import java.util.HashMap;

/**
 * Node class
 *
 * @author Gerardo Figueroa
 * Institute of Information Systems and Applications
 * National Tsing Hua University
 * Hsinchu, Taiwan
 * October 2013
 */
public class Node implements Comparable<Node> {
    
    /**
     * Public members.
     */
    public static Double DEFAULT_EDGE_WEIGHT = 0.0;
    //public HashSet<Node> edges = new HashSet<Node>();
    public HashMap<Node, Double> outgoingEdges;
    public HashMap<Node, Double> incomingEdges;
    public double rank = 0.0D;
    public String key = null;
    public boolean marked = false;
    public String data = null;

    /**
     * Private constructor.
     */
    private Node(final String key, final String data) {
        this.rank = 1.0D;
        this.key = key;
        this.data = data;
        this.outgoingEdges = new HashMap<Node, Double>();
        this.incomingEdges = new HashMap<Node, Double>();
    }

    /**
     * Compare method for sort ordering.
     */
    public int compareTo(final Node that) {
        if (this.rank > that.rank) {
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node node = (Node) o;
            if (node.key.equals(this.key)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Connect two nodes with a directional (outgoing) edge in the graph.
     */
    public void connect(final Node that, Double weight) {
        this.outgoingEdges.put(that, weight);
        that.incomingEdges.put(this, weight);
    }

    /**
     * Disconnect two nodes removing the outgoing edge in the
     * graph.
     */
    public void disconnect(final Node that) {
        this.outgoingEdges.remove(that);
        that.incomingEdges.remove(this);
    }

    /**
     * Create a unique identifier for this node, returned as a hex
     * string.
     */
    public String getId() {
        return Integer.toString(hashCode(), 16);
    }

    /**
     * Factory method.
     */
    public static Node buildNode(final Graph graph, final String key, 
            final String data) {
        Node n = graph.get(key);

        if (n == null) {
            n = new Node(key, data);
            graph.put(key, n);
        }

        return n;
    }

    @Override
    public String toString() {
        String string = "";
        string += "Key: " + this.key;
        string += ", Data: " + this.data;
        string += ", Rank: " + this.rank;
        string += ", Outgoing Edges: " + this.outgoingEdges.size();
        string += ", Incoming Edges: " + this.incomingEdges.size();
        
        return string;
    }
}
