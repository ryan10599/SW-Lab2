package com.company;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws  Exception{
        Socket clientSocket = new Socket();
        System.out.println("connecting");
        clientSocket.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.printf("connected from port %d\n", clientSocket.getLocalPort());

        Scanner scannerInput = new Scanner(System.in);




        while(true){
            System.out.println("Waiting for user input");
            String input = scannerInput.nextLine();
            clientSocket.getOutputStream().write((input+"\n").getBytes());
            clientSocket.getOutputStream().flush();
            System.out.println("sent input to server");
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            System.out.println(scanner.nextLine());
        }
    }
}
