package conway;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.startsWith;

import javax.ws.rs.ext.ContextResolver;

import org.junit.Before;
import org.junit.Test;

public class ConwayResourceTest {

  ContextResolver<Conway> resolver;
  
  @SuppressWarnings("unchecked")
  @Before public void
  setUp() {
    resolver = mock(ContextResolver.class);
  }
  
  @Test public void
  mustRenderConwayWorld() {
    when(resolver.getContext(null)).thenReturn(new Conway(new Cell(0,0)));
    ConwayResource resource = new ConwayResource(resolver);
    String board = new String(resource.renderNextGeneration());
    assertThat(board, startsWith("#····"));
  }
  
  @Test public void
  mustRenderSuccessionOfWorlds() {
    when(resolver.getContext(null)).thenReturn(new Conway(new Cell(0,0)));
    ConwayResource resource = new ConwayResource(resolver);
    resource.renderNextGeneration();
    String secondBoard = new String(resource.renderNextGeneration());
    // the lone cell has now died:
    assertThat(secondBoard, startsWith("······"));
  }
}
