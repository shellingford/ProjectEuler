package problems.common;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

public class SearchUtil {

  /**
   * Calculates min routes from the starting vertex using Djikstra's algorithm.
   *
   * @param start starting vertex
   */
  public static void dijkstra(Vertex start) {
    dijkstraImpl(start, (a, b) -> Integer.compare(a.getDistance(), b.getDistance()), (dist1, dist2) -> dist1 < dist2);
  }

  /**
   * Calculates max routes from the starting vertex using Djikstra's algorithm.
   *
   * @param start starting vertex
   */
  public static void reverseDijkstra(Vertex start) {
    dijkstraImpl(start, (a, b) -> -Integer.compare(a.getDistance(), b.getDistance()), (dist1, dist2) -> dist1 > dist2);
  }

  private static void dijkstraImpl(Vertex start, Comparator<? super Vertex> priorityComparator,
      BiFunction<Integer, Integer, Boolean> comparator) {
    start.setDistance(start.getWeight());
    PriorityQueue<Vertex> pq = new PriorityQueue<>(priorityComparator);
    pq.offer(start);

    while(!pq.isEmpty()) {
      Vertex currentNode = pq.poll();
      currentNode.getNeighbours().forEach(neighbour -> {
        int newDistance = currentNode.getDistance() + neighbour.getWeight();
        if (comparator.apply(newDistance, neighbour.getDistance())) {
          neighbour.setDistance(newDistance);
          pq.add(neighbour);
        }});
    }
  }

}
