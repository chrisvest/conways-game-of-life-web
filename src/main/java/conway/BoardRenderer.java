package conway;

import java.util.Set;

public class BoardRenderer implements ViewBoardConstraints {

  private static final byte[] emptyBoard = renderEmptyBoard();

  private static byte[] renderEmptyBoard() {
    int width = GRID_WIDTH + 2; // +1 for extra column, +1 for new-line
    int height = GRID_HEIGHT + 1; // +1 to add an extra row
    byte[] buffer = new byte[width * height];
    for (int i = 0; i < buffer.length; i++) {
      buffer[i] = ' ';
    }
    for (int i = 1; i <= height; i++) {
      int n = i * width - 1; // -1 because of zero-based indexing
      buffer[n] = '\n';
    }
    return buffer;
  }

  public byte[] render(Set<Cell> cells) {
    byte[] buffer = new byte[emptyBoard.length];
    System.arraycopy(emptyBoard, 0, buffer, 0, buffer.length);
    for (Cell cell : cells) {
      int x = cell.x;
      int y = cell.y;
      if (y < 0 || y > GRID_HEIGHT || x < 0 || x > GRID_WIDTH) {
        continue;
      }
      int lineOffset = y + y * (GRID_WIDTH + 1); // +1 for the new-line
      int offset = x + lineOffset;
      buffer[offset] = '#';
    }
    return buffer;
  }
}
