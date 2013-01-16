package conway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Conway implements Iterable<Set<Cell>> {

  private final Set<Cell> world;

  public Conway(Cell... cells) {
    this.world = new HashSet<Cell>();
    for (Cell cell : cells) {
      world.add(cell);
    }
  }

  private static Set<Cell> next(Set<Cell> world) {
    List<Cell> neighbourPlanes = shufflePlanes(world);
    Map<Cell, Integer> neighbourCounts = collapseNeighbours(neighbourPlanes);
    return filterLiveCells(neighbourCounts, world);
  }

  private static List<Cell> shufflePlanes(Set<Cell> world) {
    List<Cell> list = new ArrayList<Cell>(world.size() * 9);
    int[] deltas = new int[] {-1, 0, 1};
    for (Cell cell : world) for (int dx : deltas) for (int dy : deltas) {
      list.add(new Cell(cell.x + dx, cell.y + dy));
    }
    return list;
  }

  private static Map<Cell, Integer> collapseNeighbours(List<Cell> planeCake) {
    Map<Cell, Integer> map = new HashMap<Cell, Integer>();
    for (Cell cell : planeCake) {
      Integer existing = map.get(cell);
      if (existing == null) {
        map.put(cell, 1);
      } else {
        map.put(cell, existing + 1);
      }
    }
    return map;
  }

  private static Set<Cell> filterLiveCells(
      Map<Cell, Integer> mappedCounts, Set<Cell> existing) {
    Set<Cell> set = new HashSet<Cell>();
    for (Map.Entry<Cell, Integer> entry : mappedCounts.entrySet()) {
      Cell cell = entry.getKey();
      int count = entry.getValue();
      if (count == 3 || count >= 3 && count < 5 && existing.contains(cell)) {
        set.add(cell);
      }
    }
    return set;
  }

  @Override
  public Iterator<Set<Cell>> iterator() {
    return new ConwayIterator(world);
  }

  private static final class ConwayIterator implements Iterator<Set<Cell>> {
    private Set<Cell> currentWorld;

    public ConwayIterator(Set<Cell> initialWorld) {
      this.currentWorld = initialWorld;
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Set<Cell> next() {
      Set<Cell> world = currentWorld;
      currentWorld = Conway.next(world);
      return world;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
