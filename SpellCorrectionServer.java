import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SpellCorrectionServer {
    public static void main(String[] args) throws IOException {
        int port = 8300; // Browser will use http://localhost:8300
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new SpellCorrectionHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + port);
    }

    static class SpellCorrectionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String response;

            if (query != null && query.startsWith("word=")) {
                String word = query.substring(5).toLowerCase();
                response = "Corrected word: " + correctSpelling(word);
            } else {
                response = "Usage: http://localhost:8080/?word=helo";
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String correctSpelling(String word) {
            // Simple logic (extend later with dictionary)
            if (word.equals("helo")) return "hello";
            if (word.equals("jav")) return "java";
            if (word.equals("jekins")) return "jenkins";
            if (word.equals("wrds")) return "words";
            return word + " (no correction found)";
        }
    }
}
