package conway;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ConwayProvider implements ContextResolver<Conway> {
  public static final int GRID_WIDTH = 160;
  public static final int GRID_HEIGHT = 80;
  public static final int X_FLOOR = 40;
  public static final int X_CEIL = GRID_WIDTH - 40;
  public static final int Y_FLOOR = 20;
  public static final int Y_CEIL = GRID_HEIGHT - 20;
  public static final int INITIAL_CELL_COUNT = 80;

  @Override
  public Conway getContext(Class<?> type) {
    // The Set handles duplicates & makes sure we meet our target # of cells.
    Set<Cell> worldSet = new HashSet<Cell>();
    Random random = new Random();
    
    while (worldSet.size() < INITIAL_CELL_COUNT) {
      int x = X_FLOOR + random.nextInt(X_CEIL - X_FLOOR);
      int y = Y_FLOOR + random.nextInt(Y_CEIL - Y_FLOOR);
      worldSet.add(new Cell(x, y));
    }
    
    Cell[] world = new Cell[INITIAL_CELL_COUNT];
    return new Conway(worldSet.toArray(world));
  }
}
