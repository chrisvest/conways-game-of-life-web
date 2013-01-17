package conway;

import java.util.Set;

public class GridRenderer implements ViewGridConstraints {

  private static final byte[] emptyGrid = renderEmptyGrid();

  private static byte[] renderEmptyGrid() {
    int width = GRID_WIDTH + 2; // +1 for extra column, +1 for new-line
    int height = GRID_HEIGHT + 1; // +1 to add an extra row
    byte[] grid = new byte[width * height];
    for (int i = 0; i < grid.length; i++) {
      grid[i] = ' ';
    }
    for (int i = 1; i <= height; i++) {
      int n = i * width - 1; // -1 because of zero-based indexing
      grid[n] = '\n';
    }
    return grid;
  }

  public byte[] render(Set<Cell> cells) {
    byte[] grid = new byte[emptyGrid.length];
    System.arraycopy(emptyGrid, 0, grid, 0, grid.length);
    for (Cell cell : cells) {
      int x = cell.x;
      int y = cell.y;
      if (y < 0 || y > GRID_HEIGHT || x < 0 || x > GRID_WIDTH) {
        continue;
      }
      int lineOffset = y + y * (GRID_WIDTH + 1); // +1 for the new-line
      int offset = x + lineOffset;
      grid[offset] = '#';
    }
    return grid;
  }
}
