package conway;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Set;

import javax.ws.rs.ext.ContextResolver;

import org.junit.Test;

public class ConwayProviderTest {

  @Test public void
  mustProvideRandomConwayWithLiveCells() {
    ContextResolver<Conway> provider = new ConwayProvider();
    Conway conway = provider.getContext(null);
    Set<Cell> world = conway.iterator().next();
    assertThat(world.size(), is(ConwayProvider.INITIAL_CELL_COUNT));
    for (Cell cell : world) {
      assertThat(cell.x, allOf(
          greaterThanOrEqualTo(ConwayProvider.X_FLOOR),
          lessThanOrEqualTo(ConwayProvider.X_CEIL)));
      assertThat(cell.y, allOf(
          greaterThanOrEqualTo(ConwayProvider.Y_FLOOR),
          lessThanOrEqualTo(ConwayProvider.Y_CEIL)));
    }
  }
}
