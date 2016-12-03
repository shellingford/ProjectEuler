package problems.common;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
  private String name;
  private int weight;
  private int distance = Integer.MAX_VALUE;
  private Set<Vertex> neighbours = new HashSet<>();

  public Vertex(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public Vertex(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public Set<Vertex> getNeighbours() {
    return neighbours;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vertex other = (Vertex) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return name + ":" + weight;
  }
}

