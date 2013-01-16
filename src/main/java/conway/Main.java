package conway;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class Main {

  public static void main(String[] args) throws Exception {
    int port = Integer.getInteger("port", 8080);
    URI baseUri = getBaseUrl(port);
    ResourceConfig rc = new PackagesResourceConfig("conway");
    GrizzlyServerFactory.createHttpServer(baseUri, rc);
    
    System.in.read();
  }

  private static URI getBaseUrl(int port) {
    String url = System.getProperty("url");
    if (url == null) {
      return UriBuilder.fromUri("http://localhost/").port(port).build();
    }
    try {
      return new URI(url);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
