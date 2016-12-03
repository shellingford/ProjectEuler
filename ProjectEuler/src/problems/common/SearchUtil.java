package problems.common;

import java.util.PriorityQueue;

public class SearchUtil {

  public static void dijkstra(Vertex start) {
    start.setDistance(start.getWeight());
    PriorityQueue<Vertex> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.getDistance(), b.getDistance()));
    pq.offer(start);

    while(!pq.isEmpty()) {
      Vertex currentNode = pq.poll();
      currentNode.getNeighbours().forEach(neighbour -> {
        int newDistance = currentNode.getDistance() + neighbour.getWeight();
        if (newDistance < neighbour.getDistance()) {
          neighbour.setDistance(newDistance);
          pq.add(neighbour);
        }});
    }
  }

}
