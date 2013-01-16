package conway;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class ConwayTest {
  
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
  
  public static <T> org.hamcrest.Matcher<T> noneOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second) {
    return org.hamcrest.core.AllOf.<T>allOf(not(first), not(second));
  }
  
  @Test public void
  canSeeLiveCells() {
    assertThat(first(new Conway(new Cell(0,0))), hasItem(new Cell(0,0)));
  }
  
  @Test public void
  loneCellsDie() {
    assertThat(second(new Conway(new Cell(0,0))), emptyIterable());
  }
  
  @Test public void
  cellsWithTwoNeighboursSurvive() {
    Conway game = new Conway(new Cell(0,0), new Cell(0,1), new Cell(0,2));
    assertThat(second(game), hasItem(new Cell(0,1)));
    assertThat(second(game), noneOf(
        hasItem(new Cell(0,0)),
        hasItem(new Cell(0,2))));
  }
  
  @Test public void
  cellsWithThreeNeighboursComeAlive() {
    Conway game = new Conway(new Cell(0,0), new Cell(0,1), new Cell(0,2));
    assertThat(second(game), allOf(
        hasItem(new Cell(1,1)),
        hasItem(new Cell(-1,1))));
    assertThat(second(game), noneOf(
        hasItem(new Cell(-1,2)),
        hasItem(new Cell(-1,-2))));
    assertThat(second(game), noneOf(
        hasItem(new Cell(1,2)),
        hasItem(new Cell(1,-2))));
  }
  
  @Test public void
  cellsWithFourOrMoreNeighboursDieOfOverpopulation() {
    Cell centerCell = new Cell(1,1);
    Conway game = new Conway(
        new Cell(0,1), new Cell(2,1), centerCell, new Cell(1,0), new Cell(1,2));
    assertThat(second(game), not(hasItem(centerCell)));
  }
}
