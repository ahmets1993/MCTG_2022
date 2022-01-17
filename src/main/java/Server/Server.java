package Server;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/v1", new HttpHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on port 8001");
    }
}
