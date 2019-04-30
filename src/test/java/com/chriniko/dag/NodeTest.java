package com.chriniko.dag;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NodeTest {

    @Test
    public void add_child_node_case() {
        // given
        Node rootNode = new Node();
        rootNode.setLabel("Football");

        Node competition = new Node();
        rootNode.setLabel("Competition");


        // when
        rootNode.addChild(competition);


        // then
        Assert.assertEquals(1, rootNode.getChildren().size());

        Assert.assertEquals(1, competition.getParents().size());

        Assert.assertSame(competition.getParents().iterator().next(), rootNode);
        Assert.assertEquals(0, competition.getChildren().size());

        Assert.assertTrue(rootNode.getParents().isEmpty());
    }

    @Test
    public void add_parent_node_case() {

        // given
        Node rootNode = new Node();
        rootNode.setLabel("Football");

        Node competition = new Node();
        rootNode.setLabel("Competition");

        // when
        competition.addParent(rootNode);

        // then
        Assert.assertEquals(1, rootNode.getChildren().size());

        Assert.assertEquals(1, competition.getParents().size());

        Assert.assertSame(competition.getParents().iterator().next(), rootNode);
        Assert.assertEquals(0, competition.getChildren().size());

        Assert.assertTrue(rootNode.getParents().isEmpty());
    }

    @Test
    public void get_node_children_case() {

        // given
        Node rootNode = new Node();
        rootNode.setLabel("Football");

        List<Node> children = IntStream
                .rangeClosed(1, 5)
                .boxed()
                .map(idx -> {
                    Node n = new Node();
                    n.setLabel("Competition " + idx);
                    return n;
                })
                .collect(Collectors.toList());

        rootNode.addChildren(children);

        // when
        Set<Node> result = rootNode.getChildren();

        // then
        Assert.assertEquals(5, result.size());
    }

    @Test
    public void get_node_children_descendant_nodes_recursion_case() {

        // given
        Node football = new Node();
        football.setLabel("Football");

        Node competition = new Node();
        competition.setLabel("Competition");

        Node player = new Node();
        player.setLabel("Player");

        Node premiership = new Node();
        premiership.setLabel("Premiership");

        Node championsLeague = new Node();
        championsLeague.setLabel("Champions League");

        Node valencia = new Node();
        valencia.setLabel("Valencia");

        Node manCity = new Node();
        manCity.setLabel("Man City");

        Node manUtd = new Node();
        manUtd.setLabel("Man Utd");

        football.addChildren(Arrays.asList(competition, player));

        competition.addChildren(Arrays.asList(premiership, championsLeague));

        premiership.addChildren(Arrays.asList(manCity, manUtd));

        championsLeague.addChildren(Arrays.asList(manUtd, valencia));


        // when
        Set<Node> result = competition.getChildrenDescendantWithRecursion();


        // then
        result.forEach(System.out::println);
        Assert.assertEquals(5, result.size());

        System.out.println();


        // when
        Set<Node> result2 = football.getChildrenDescendantWithRecursion();


        // then
        result2.forEach(System.out::println);
        Assert.assertEquals(7, result2.size());

        System.out.println();
    }

    @Test
    public void get_node_children_descendant_nodes_iteration_case() {

        // given
        Node football = new Node();
        football.setLabel("Football");

        Node competition = new Node();
        competition.setLabel("Competition");

        Node player = new Node();
        player.setLabel("Player");

        Node premiership = new Node();
        premiership.setLabel("Premiership");

        Node championsLeague = new Node();
        championsLeague.setLabel("Champions League");

        Node valencia = new Node();
        valencia.setLabel("Valencia");

        Node manCity = new Node();
        manCity.setLabel("Man City");

        Node manUtd = new Node();
        manUtd.setLabel("Man Utd");

        football.addChildren(Arrays.asList(competition, player));

        competition.addChildren(Arrays.asList(premiership, championsLeague));

        premiership.addChildren(Arrays.asList(manCity, manUtd));

        championsLeague.addChildren(Arrays.asList(manUtd, valencia));


        // when
        Set<Node> result = competition.getChildrenDescendantWithIteration();


        // then
        result.forEach(System.out::println);
        Assert.assertEquals(5, result.size());

        System.out.println();


        // when
        Set<Node> result2 = football.getChildrenDescendantWithIteration();


        // then
        result2.forEach(System.out::println);
        Assert.assertEquals(7, result2.size());

        System.out.println();
    }

    @Test
    public void get_node_all_parents_node_recursion_case() {

        // given
        Node football = new Node();
        football.setLabel("Football");

        Node competition = new Node();
        competition.setLabel("Competition");

        Node player = new Node();
        player.setLabel("Player");

        Node premiership = new Node();
        premiership.setLabel("Premiership");

        Node championsLeague = new Node();
        championsLeague.setLabel("Champions League");

        Node valencia = new Node();
        valencia.setLabel("Valencia");

        Node manCity = new Node();
        manCity.setLabel("Man City");

        Node manUtd = new Node();
        manUtd.setLabel("Man Utd");

        football.addChildren(Arrays.asList(competition, player));

        competition.addChildren(Arrays.asList(premiership, championsLeague));

        premiership.addChildren(Arrays.asList(manCity, manUtd));

        championsLeague.addChildren(Arrays.asList(manUtd, valencia));


        // when
        Set<Node> results = manUtd.getAllParentsWithRecursion();


        // then
        results.forEach(System.out::println);
        Assert.assertEquals(4, results.size());

    }

    @Test
    public void get_node_all_parents_node_iteration_case() {

        // given
        Node football = new Node();
        football.setLabel("Football");

        Node competition = new Node();
        competition.setLabel("Competition");

        Node player = new Node();
        player.setLabel("Player");

        Node premiership = new Node();
        premiership.setLabel("Premiership");

        Node championsLeague = new Node();
        championsLeague.setLabel("Champions League");

        Node valencia = new Node();
        valencia.setLabel("Valencia");

        Node manCity = new Node();
        manCity.setLabel("Man City");

        Node manUtd = new Node();
        manUtd.setLabel("Man Utd");

        football.addChildren(Arrays.asList(competition, player));

        competition.addChildren(Arrays.asList(premiership, championsLeague));

        premiership.addChildren(Arrays.asList(manCity, manUtd));

        championsLeague.addChildren(Arrays.asList(manUtd, valencia));


        // when
        Set<Node> results = manUtd.getAllParentsWithIteration();


        // then
        results.forEach(System.out::println);
        Assert.assertEquals(4, results.size());

    }

}