package conway;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConwayTest {
  @Test public void
  canSeeLiveCells() {
    assertThat(first(new Conway(new Cell(0,0))), hasItem(new Cell(0,0)));
  }

  private <T> T first(Iterable<T> iterable) {
    return iterable.iterator().next();
  }
}
