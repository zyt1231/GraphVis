package com.yuting.newsarticle.models;

import java.util.List;
import java.util.Set;

/**
 * Created by Ting on 5/3/17.
 */
public class Network {
    private Set<Node> nodes;
    private Set<Edge> edges;

    public Network() {
    }

    public Network(Set<Node> nodes, Set<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}
