package org.theitside;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyHttpServer {
	
	private static String CONTEXT 	= "/consent";
	private static int PORT_NUMBER 	= 8080;	

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT_NUMBER), 0);
        server.createContext(CONTEXT, new MyHandler());
        server.setExecutor(null);
        server.start();
	}
	
	static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	
        	System.out.println("Request Method: " + t.getRequestMethod() );
        	
        	Headers h = t.getRequestHeaders();
        	System.out.println("Request Headers: ");
        	for( Entry<String, List<String>> s : h.entrySet() ) {
        		System.out.println("-- " + s.getKey() + " :: " + s.getValue().toString() ); 
        	}
        	System.out.println("\r\n");
        	
        	InputStream is = t.getRequestBody();
        	
        	Scanner s = new Scanner(is).useDelimiter("\\A");
        	String requestBody = s.hasNext() ? s.next() : "";
        	
        	System.out.println("Request Body: " + requestBody);
        	
        	is.close();
        	s.close();	
        	
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}