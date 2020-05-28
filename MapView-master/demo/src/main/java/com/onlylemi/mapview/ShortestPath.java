package com.onlylemi.mapview;


import java.util.*;

public class ShortestPath {
    private static final Graph.Edge[] GRAPH = {


            // 2 ЛИНИЯ
            new Graph.Edge("Парнас", "Проспект Просвещения", 3),
            new Graph.Edge("Проспект Просвещения", "Озерки", 3),
            new Graph.Edge("Озерки", "Удельная", 3),
            new Graph.Edge("Удельная", "Пионерская", 3),
            new Graph.Edge("Пионерская", "Черная речка", 3),
            new Graph.Edge("Черная речка", "Петроградская", 3),
            new Graph.Edge("Петроградская", "Горьковская", 3),
            new Graph.Edge("Горьковская", "Невский проспект", 3),
            new Graph.Edge("Невский проспект", "Гостиный двор", 3),
            new Graph.Edge("Невский проспект", "Сенная площадь", 3),
            new Graph.Edge("Сенная площадь", "Спасская", 3),
            new Graph.Edge("Сенная площадь", "Садовая", 3),
            new Graph.Edge("Садовая", "Спасская", 3),
            new Graph.Edge("Сенная площадь", "Технологический институт - 2", 3),
            new Graph.Edge("Технологический институт - 2", "Технологический институт - 1", 3),
            new Graph.Edge("Технологический институт - 2", "Фрунзенская", 3),
            new Graph.Edge("Фрунзенская", "Московские ворота", 3),
            new Graph.Edge("Московские ворота", "Электросила", 3),
            new Graph.Edge("Электросила", "Парк Победы", 3),
            new Graph.Edge("Парк Победы", "Московская", 3),
            new Graph.Edge("Московская", "Звездная", 3),
            new Graph.Edge("Звездная", "Купчино", 3),

            // 1 ЛИНИЯ
            new Graph.Edge("Девяткино", "Гражданский проспект", 3),
            new Graph.Edge("Гражданский проспект", "Академическая", 3),
            new Graph.Edge("Академическая", "Политехническая", 3),
            new Graph.Edge("Политехническая", "Площадь Мужества", 3),
            new Graph.Edge("Площадь Мужества", "Лесная", 3),
            new Graph.Edge("Лесная", "Выборгская", 3),
            new Graph.Edge("Выборгская", "Площадь Ленина", 3),
            new Graph.Edge("Площадь Ленина", "Чернышевская", 3),
            new Graph.Edge("Чернышевская", "Площадь Восстания", 3),
            new Graph.Edge("Площадь Восстания", "Маяковская", 3),
            new Graph.Edge("Площадь Восстания", "Владимирская", 3),
            new Graph.Edge("Владимирская", "Достоевская", 3),
            new Graph.Edge("Владимирская", "Пушкинская", 3),
            new Graph.Edge("Пушкинская", "Звенигородская", 3),
            new Graph.Edge("Пушкинская", "Технологический институт - 1", 3),
            new Graph.Edge("Технологический институт - 1", "Балтийская", 3),
            new Graph.Edge("Балтийская", "Нарвская", 3),
            new Graph.Edge("Нарвская", "Кировский завод", 3),
            new Graph.Edge("Кировский завод", "Автово", 3),
            new Graph.Edge("Автово", "Ленинский проспект", 3),
            new Graph.Edge("Ленинский проспект", "Проспект Ветеранов", 3),


            // 3 ЛИНИЯ
            new Graph.Edge("Беговая", "Новокрестовская", 3),
            new Graph.Edge("Новокрестовская", "Приморская", 3),
            new Graph.Edge("Приморская", "Василеостровская", 3),
            new Graph.Edge("Василеостровская", "Гостиный двор", 3),
            new Graph.Edge("Гостиный двор", "Маяковская", 3),
            new Graph.Edge("Маяковская", "Площадь Александра Невского - 1", 3),
            new Graph.Edge("Площадь Александра Невского - 1", "Площадь Александра Невского - 2", 3),
            new Graph.Edge("Площадь Александра Невского - 1", "Елизаровская", 3),
            new Graph.Edge("Елизаровская", "Ломоносовская", 3),
            new Graph.Edge("Ломоносовская", "Пролетарская", 3),
            new Graph.Edge("Пролетарская", "Обухово", 3),
            new Graph.Edge("Обухово", "Рыбацкое", 3),


            // 4 ЛИНИЯ
            new Graph.Edge("Спасская", "Достоевская", 3),
            new Graph.Edge("Достоевская", "Лиговский проспект", 3),
            new Graph.Edge("Лиговский проспект", "Площадь Александра Невского - 2", 3),
            new Graph.Edge("Площадь Александра Невского - 2", "Новочеркасская", 3),
            new Graph.Edge("Новочеркасская", "Ладожская", 3),
            new Graph.Edge("Ладожская", "Проспект Большевиков", 3),
            new Graph.Edge("Проспект Большевиков", "Улица Дыбенко", 3),


            // 5 ЛИНИЯ
            new Graph.Edge("Комендантский проспект", "Старая Деревня", 3),
            new Graph.Edge("Старая Деревня", "Крестовский остров", 3),
            new Graph.Edge("Крестовский остров", "Чкаловская", 3),
            new Graph.Edge("Чкаловская", "Спортивная", 3),
            new Graph.Edge("Спортивная", "Адмиралтейская", 3),
            new Graph.Edge("Адмиралтейская", "Садовая", 3),
            new Graph.Edge("Садовая", "Звенигородская", 3),
            new Graph.Edge("Звенигородская", "Обводный канал", 3),
            new Graph.Edge("Обводный канал", "Волковская", 3),
            new Graph.Edge("Волковская", "Бухарестская", 3),
            new Graph.Edge("Бухарестская", "Международная", 3),
            new Graph.Edge("Международная", "Проспект Славы", 3),
            new Graph.Edge("Проспект Славы", "Дунайская", 3),
            new Graph.Edge("Дунайская", "Шушары", 3),

    };



    public static void GraphFind (String Start, String End) {
        final String START = Start;
        final String END = End;
        Graph g = new Graph(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        //g.printAllPaths();
    }
    public static void main(String[] args) {

    }
}

class Graph {
    private final Map<String, Vertex> graph;


    public static class Edge {
        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE;
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();
        public Vertex(String name) {
            this.name = name;
        }

        private void printPath(String End) {
            int EndDis = 0;
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath(End);
                System.out.printf(" -> %s(%d)", this.name, this.dist);
                if (this.name == End) {
                    EndDis = this.dist;
                    System.out.printf(" Итог: (%d)", EndDis);
                    MarkLayerTestActivity c1 = new MarkLayerTestActivity();
                    c1.distance = EndDis;
                }
            }
        }

        public int compareTo(Vertex other) {
            if (dist == other.dist)
                return name.compareTo(other.name);

            return Integer.compare(dist, other.dist);
        }

        @Override
        public String toString() {
            return "(" + name + ", " + dist + ")";
        }
    }

    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
        }


        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist);
        }
    }

    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }


    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE)
                break;


            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }


    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath(endName);
        System.out.println();
    }


    public void printAllPaths() {
        for (Vertex v : graph.values()) {
          //  v.printPath();
            System.out.println();
        }
    }
}