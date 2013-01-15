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
  
  private static Set<Cell> next(Set<Cell> world) {
    return new HashSet<Cell>();
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
