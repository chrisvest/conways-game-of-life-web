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
  
  private final Iterator<Set<Cell>> worldSequence;
  private final BoardRenderer renderer;

  public ConwayResource(@Context ContextResolver<Conway> conwayProvider) {
    Conway conway = conwayProvider.getContext(null);
    worldSequence = conway.iterator();
    renderer = new BoardRenderer();
  }
  
  @GET
  @Produces("text/plain")
  public byte[] renderNextGeneration() {
    return renderer.render(worldSequence.next());
  }
}
