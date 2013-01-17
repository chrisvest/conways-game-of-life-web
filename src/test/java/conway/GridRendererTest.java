package conway;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GridRendererTest implements ViewGridConstraints {
  private static final byte CELL = '#';
  
  GridRenderer renderer;
  
  @Before public void
  setUp() {
    renderer = new GridRenderer();
  }

  private Byte[] render(Set<Cell> cells) {
    // Generics vs. Primitive Arrays... Fight!
    byte[] bytes = renderer.render(cells);
    Byte[] bigBytes = new Byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      bigBytes[i] = bytes[i];
    }
    return bigBytes;
  }

  private Set<Cell> setOf(Cell... cells) {
    Set<Cell> set = new HashSet<Cell>(cells.length);
    for (Cell cell : cells) {
      set.add(cell);
    }
    return set;
  }
  
  @Test public void
  renderNoCells() {
    HashSet<Cell> cells = new HashSet<Cell>();
    assertThat(render(cells), not(hasItemInArray(CELL)));
  }
  
  @Test public void
  mustRenderCellInTopLeftCorner() {
    byte[] bytes = renderer.render(setOf(new Cell(0,0)));
    assertThat(bytes[0], is(CELL));
  }
  
  @Test public void
  mustRenderCellInLowerRightCorner() {
    byte[] bytes = renderer.render(setOf(new Cell(GRID_WIDTH, GRID_HEIGHT)));
    // -1 for zero-based, -1 for new-line
    assertThat(bytes[bytes.length - 2], is(CELL));
  }
  
  @Test public void
  mustNotRenderCellsAboveBoard() {
    assertThat(render(setOf(new Cell(0,-1))), not(hasItemInArray(CELL)));
  }
  
  @Test public void
  mustNotRenderCellsBellowBoard() {
    assertThat(render(setOf(new Cell(0, GRID_HEIGHT + 1))), not(hasItemInArray(CELL)));
  }
  
  @Test public void
  mustNotRenderCellsLeftOfBoard() {
    assertThat(render(setOf(new Cell(-1, 0))), not(hasItemInArray(CELL)));
  }
  
  @Test public void
  mustNotRenderCellsRightOfBoard() {
    assertThat(render(setOf(new Cell(GRID_WIDTH + 1, 0))), not(hasItemInArray(CELL)));
  }
}
