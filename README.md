# simplehttp
This is a simple HTTP server that dumps the request headers and body.

I needed something to see what an application was sending to an HTTP server so I decided to put together a really simple HTTP that uses basic native Java capabilites i.e. no Apache HTTP server.

In order to run the server, first compile the code:

javac src/org/theitside/MyHttpServer.java

Then run:

cd src
java org.theitside.MyHttpServer

Hope you find this useful.
