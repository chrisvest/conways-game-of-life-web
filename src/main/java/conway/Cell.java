package conway;

public class Cell {
  public final int x;
  public final int y;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Cell) {
      Cell that = (Cell) obj;
      return this.x == that.x
          && this.y == that.y;
    }
    return false;
  }
}
