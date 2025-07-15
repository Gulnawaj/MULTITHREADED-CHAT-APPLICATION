# MULTITHREADED-CHAT-APPLICATION

#company - CODTECH IT SOLUTIONS

#name - GULNAWAJ

#intern id - CT04DG2684

#domain - JAVA PROGRAMMING

#duration - 4WEEKS

#mentor - NEELA SANTOSH

#DESCRIPTION 

In this project, I have created a simple client-server chat application using Java Sockets and Multithreading.
The main aim of this project is to understand how real-time communication works between multiple clients with the help of a single server.
I have divided this project into two Java files:

MultithreadChatApplication.java
This file contains the server-side code.
The server listens on port 12345 using ServerSocket.
Whenever a new client connects, the server accepts the connection and creates a new thread (ClientHandler) for that client.
This is done using multithreading, so multiple clients can chat with each other at the same time.
The server keeps track of all connected clients in a HashSet.
When a client sends a message, the server broadcasts that message to all other connected clients.
If any client disconnects, the server removes that client from the list and informs others.

ChatClient.java
This file is for the client-side program.
Any number of clients can run this file at the same time (in different terminals or PCs).
When we run the client:
It connects to the server on localhost and port 12345.
It asks the user to enter their name.
It has two threads:
ReadThread — keeps listening to messages from the server and displays them.
WriteThread — takes input from the user (using Scanner) and sends it to the server.
So, if there are 3 clients connected, and any one client types a message, the other two clients will immediately see that message on their screens.
Each client can type exit to leave the chat.

#OUTPUT
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/d0c6f4b6-c3e9-4483-b498-ff456dee1834" />

<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/427589d8-f354-4358-a430-edb2f77de5a1" />


