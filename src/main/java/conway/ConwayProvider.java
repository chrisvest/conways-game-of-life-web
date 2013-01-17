package conway;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ConwayProvider implements ContextResolver<Conway>, ViewGridConstraints {

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
