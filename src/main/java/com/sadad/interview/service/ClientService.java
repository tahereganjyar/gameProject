package com.sadad.interview.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class ClientService {

    Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;

    /**
     * This method starts a connection to a client.
     * By creating reader and writer, client is ready to use them in play method
     * @param clientSocket
     */

    public void startConnection(Socket clientSocket) {

        try {
            socket = clientSocket;

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);


        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }


    }

    /**
     * This method start a play with the given number by player.
     * In order to listen other player, a while loop wait to receive answer from other player.
     * After receiving answer, it sent to UI
     * @param num
     * @return
     */
    public String play(Long num) {


        try {
            if (!num.equals(-1L)) {

                String input = String.valueOf(num);
                out.println("tahere:" + (Integer.valueOf(input)));
            }
            String result;
            while (true) {

                String response = in.readLine();
                result = response;
                break;


            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
