package com.sadad.interview.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

@Service
public class ServerService {
    private int port;
    BufferedReader in = null;
    PrintWriter out;
    int xValue = 0;
    int maxValue = 0;
    Socket socket = null;
    ServerSocket server = null;

    /**
     * This method start a connection for a server. There is the heart of this game when an random number is crrated that is answer and all player must find it.
     * After the guss numbers rached to 100, players loose the game.
     * A thread at the end of this method is responsible to accept any new connection to itself so it waits for it to happend
     * @param port
     * @return
     */
    public boolean start(int port) {


        Random rand = new Random();
        //finds a random number
        xValue = rand.nextInt(10) + 1;
        System.out.println("****************************************************");
        System.out.println("************** XVALUE IS :" + xValue + " ***************");
        System.out.println("****************************************************");

        //the max numner
        maxValue = 100;

        this.port = port;
        MyThread myThread = new MyThread();
        myThread.start();

        return true;

    }

    public class MyThread extends Thread {

        public void run() {
            System.out.println("MyThread running");

            try {
                server = new ServerSocket(port);
                socket = server.accept();
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException i) {
                System.out.println(i);
            }
        }
    }

    /**
     * This method compare received number form client to correct number then send appropriate response
     * @param num
     * @return
     */
    public String play(Long num) {

        try {


            String input = String.valueOf(num);

            if (Integer.valueOf(input) == xValue) {
                out.println("WINNER IS MASTER. X WAS " + xValue);
                return "****** YOU ARE WINNER. X WAS " + xValue + " ******";
            }
            int finalResult = Integer.valueOf(input) * xValue;
            if (finalResult >= maxValue) {
                out.println("master game over. max value reached!!!");
                return "GAME OVER. MAX VALUE REACHED!!!!";
            }
            out.println("computer:" + finalResult);
            String result = "UNKNOWN";
            String messageForClient;
            while (true) {

                String response = in.readLine();
                Integer clientResult = Integer.valueOf(response.substring(7)) * xValue;

                if (Integer.valueOf(response.substring(7)) == xValue) {
                    messageForClient = "****** YOU ARE WINNER. X WAS " + xValue + " ******";
                    out.println(messageForClient);


                } else if (clientResult >= 100) {
                    messageForClient = "GAME OVER. MAX VALUE REACHED!!!!";
                    out.println(messageForClient);

                } else {

                    result = "tahere:" + clientResult;

                }
                break;

            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
