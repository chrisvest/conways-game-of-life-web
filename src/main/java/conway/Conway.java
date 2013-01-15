package conway;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Conway implements Iterable<Set<Cell>> {

  private final Set<Cell> world;

  public Conway(Cell... cells) {
    this.world = new HashSet<Cell>();
    for (Cell cell : cells) {
      world.add(cell);
    }
  }

  @Override
  public Iterator<Set<Cell>> iterator() {
    return new ConwayIterator(world);
  }

  private static final class ConwayIterator implements Iterator<Set<Cell>> {
    private Set<Cell> world;

    public ConwayIterator(Set<Cell> world) {
      this.world = world;
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Set<Cell> next() {
      return world;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
