package conway;

import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/")
public class ConwayResource implements ViewBoardConstraints {
  
  private Iterator<Set<Cell>> worldSequence;

  public ConwayResource(@Context ContextResolver<Conway> conwayProvider) {
    Conway conway = conwayProvider.getContext(null);
    worldSequence = conway.iterator();
  }
  
  @GET
  @Produces("text/plain")
  public char[] renderNextGeneration() {
    return render(worldSequence.next());
  }

  private static final char[] emptyBoard = renderEmptyBoard();

  private static char[] renderEmptyBoard() {
    int pixels = GRID_WIDTH * GRID_HEIGHT + GRID_HEIGHT;
    int lineLength = GRID_WIDTH + 1;
    char[] buffer = new char[pixels];
    for (int i = 0; i <= GRID_WIDTH; i++) {
      buffer[i] = '·';
    }
    buffer[lineLength] = '\n';
    for (int i = 1; i < GRID_HEIGHT; i++) {
      System.arraycopy(buffer, 0, buffer, i * lineLength, lineLength);
    }
    return buffer;
  }
  
  private static char[] render(Set<Cell> world) {
    char[] buffer = new char[emptyBoard.length];
    System.arraycopy(emptyBoard, 0, buffer, 0, buffer.length);
    for (Cell cell : world) {
      int lineOffset = cell.y * GRID_WIDTH;
      int offset = cell.x + lineOffset + (lineOffset % GRID_WIDTH);
      buffer[offset] = '#';
    }
    return buffer;
  }
}
