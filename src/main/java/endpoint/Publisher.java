package endpoint;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class Publisher {

	public static void main(String[] args) throws IOException {
        // HttpServer server = HttpServer.createSimpleServer();
        // create jersey-grizzly server
        ResourceConfig rc = new PackagesResourceConfig("rest");
        HttpServer server = GrizzlyServerFactory.createHttpServer(
                "http://localhost:8088", rc);
        try {
            server.start();
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
