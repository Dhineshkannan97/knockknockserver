package sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class knockKnockServer {
    public static void main(String args[])  {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 4444 + ", " + e);
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true){
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: " + 4444 + ", " + e);
            System.exit(1);
        }
        try {
            DataInputStream is = new DataInputStream(
                    new BufferedInputStream(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(
                    new BufferedOutputStream(clientSocket.getOutputStream(), 1024), false);
            KnockFun kkf = new KnockFun();
            String inputLine, outputLine;

            outputLine = kkf.processInput(null);
            os.println(outputLine);
            os.flush();
            while ((inputLine = is.readLine()) != null) {
                outputLine = kkf.processInput(inputLine);
                File file = new File("C:\\Users\\Dhinesh Kannan\\IdeaProjects\\knockknockswerver\\src\\main\\resources\\punchline.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(outputLine);
                bw.close();
                os.println(outputLine);
                os.flush();
                if (outputLine.equals("Bye"))
                    break;
            }
            os.close();
            is.close();
//            clientSocket.close();
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}}
    }


