package com.chriniko.dag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@Data
@ToString(of = {"label"})
@EqualsAndHashCode(of = {"label"})
public class Node {

    private String label;

    private final Set<Node> parents; // if it is empty is the root of the DAG.
    private final Set<Node> children;

    public Node() {
        parents = new LinkedHashSet<>();
        children = new LinkedHashSet<>();
    }

    public void addChild(Node child) {
        Objects.requireNonNull(child);

        child.getParents().add(this);
        children.add(child);
    }

    public void addParent(Node parent) {
        Objects.requireNonNull(parent);

        this.getParents().add(parent);
        parent.addChild(this);
    }

    public void addChildren(List<Node> children) {
        Objects.requireNonNull(children);

        children.forEach(this::addChild);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Set<Node> getChildrenDescendantWithRecursion() {
        Set<Node> accumulator = new LinkedHashSet<>();
        childrenDescendantProcessor(this, accumulator);
        return accumulator;
    }

    private void childrenDescendantProcessor(Node n, Set<Node> accumulator) {
        if (!n.getChildren().isEmpty()) {
            accumulator.addAll(n.getChildren());

            for (Node node : n.getChildren()) {
                childrenDescendantProcessor(node, accumulator);
            }
        }
    }

    public Set<Node> getChildrenDescendantWithIteration() {
        Set<Node> accumulator = new LinkedHashSet<>();

        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(this);

        while (!toVisit.isEmpty()) {
            Node n = toVisit.poll();

            if (!n.getChildren().isEmpty()) {
                accumulator.addAll(n.getChildren());

                toVisit.addAll(n.getChildren());
            }
        }

        return accumulator;
    }
    // -----------------------------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------------------------------
    public Set<Node> getAllParentsWithRecursion() {
        Set<Node> accumulator = new LinkedHashSet<>();
        parentsProcessor(this, accumulator);
        return accumulator;
    }

    private void parentsProcessor(Node n, Set<Node> accumulator) {
        if (!n.getParents().isEmpty()) {
            accumulator.addAll(n.getParents());

            for (Node parentNode : n.getParents()) {
                parentsProcessor(parentNode, accumulator);
            }
        }
    }

    public Set<Node> getAllParentsWithIteration() {
        Set<Node> accumulator = new LinkedHashSet<>();

        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(this);

        while (!toVisit.isEmpty()) {

            Node n = toVisit.poll();
            if (!n.getParents().isEmpty()) {
                accumulator.addAll(n.getParents());

                toVisit.addAll(n.getParents());
            }
        }

        return accumulator;
    }
    // -----------------------------------------------------------------------------------------------------------------

}