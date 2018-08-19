package com.sadad.interview.service;

import java.io.IOException;
import java.net.Socket;

import com.sadad.interview.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {


    private ServerService serverService;
    private ClientService clientService;

    @Autowired
    public PlayService(ServerService greetServer, ClientService greetClient) {

        this.clientService = greetClient;
        this.serverService = greetServer;

    }

    /**
     * this method starts a play with the given number. It is important to know that the
     * current player is master or client because the heart of this game is in server servic
     * @param num
     * @param isMaster
     * @return
     */
    public ResponseDto playWithNumber(Long num, boolean isMaster) {
        String resultFromAnotherPlayer;
        if (isMaster) {
            resultFromAnotherPlayer = serverService.play(num);

        } else {

            resultFromAnotherPlayer = clientService.play(num);
        }

        ResponseDto ev = new ResponseDto();
        ev.setTitle(resultFromAnotherPlayer);
        return ev;

    }

    /**
     * This method open a socket for client or server. If already a socket has been opened
     * then a client can connect to it. If not, an exception occurred and socket opened for a master
     *
     * @param
     */
    public boolean openSocket() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 6666);
            clientService.startConnection(clientSocket);
            System.out.println("ara you ready? so enter a number! ");


            return false;
        } catch (IOException e) {

            boolean start = serverService.start(6666);

        }
        return true;

    }


}
