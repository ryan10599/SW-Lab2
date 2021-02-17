package com.company;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("Listening to port: 8080");

        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.printf("Client connected %s:%d\n", clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());

            Thread t1 = new Thread(new ClientHandler(clientSocket,serverSocket));
            t1.start();
        }

    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    public ClientHandler(Socket clientSocket, ServerSocket serverSocket) {this.clientSocket = clientSocket;}

    @Override
    public void run(){
        try{
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            while (scanner.hasNext()){
                String clientInput = scanner.nextLine();
                System.out.printf("Got from client: %s\n", clientInput);
                clientSocket.getOutputStream().write(("gg\n").getBytes());
                clientSocket.getOutputStream().flush();
            }
        }catch (Exception e){
            //do noting
        }
    }

}
