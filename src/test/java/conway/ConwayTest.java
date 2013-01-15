package conway;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class ConwayTest {
  @Test public void
  canSeeLiveCells() {
    assertThat(first(new Conway(new Cell(0,0))), hasItem(new Cell(0,0)));
  }
  
  @Test public void
  loneCellsDie() {
    assertThat(second(new Conway(new Cell(0,0))), emptyIterable());
  }

  private <T> T first(Iterable<T> iterable) {
    return generation(0, iterable);
  }

  private <T> T second(Iterable<T> iterable) {
    return generation(1, iterable);
  }

  private <T> T generation(int x, Iterable<T> iterable) {
    Iterator<T> iterator = iterable.iterator();
    T obj = null;
    for (int i = 0; i <= x; i++) {
      obj = iterator.next();
    }
    return obj;
  }
}
