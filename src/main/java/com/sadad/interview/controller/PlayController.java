package com.sadad.interview.controller;

import java.util.ArrayList;
import java.util.List;

import com.sadad.interview.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sadad.interview.service.PlayService;

@RestController
@RequestMapping("/api/game")
public class PlayController {


    private PlayService playService;

    @Autowired
    public PlayController(PlayService playService) {

        this.playService = playService;

    }

    /**
     * This method open a socket for a connection between master and client.
     * Then indicates that the opened socket is opened for a master or for a client
     * @return
     */
    @RequestMapping(value = "/openSocket", method = RequestMethod.GET)
    public List<ResponseDto> openSocket() {
        String resultMsg;
        if (this.playService.openSocket()) {
            resultMsg = "you are playing as a master";

        } else {

            resultMsg = "you are playing as a client";


        }
        List<ResponseDto> allResp = new ArrayList<ResponseDto>();

        ResponseDto resp = new ResponseDto();
        resp.setTitle(resultMsg);
        allResp.add(resp);

        return allResp;

    }

    /**
     *
     * @param num :selected number that player entered
     * @param isMaster :indicates that the current player is master or client
     * @return
     */

    @RequestMapping(value = "/play/{num}/{isMaster}", method = RequestMethod.GET)
    public List<ResponseDto> playWithNumber(
            @PathVariable(value = "num") Long num, @PathVariable(value = "isMaster") boolean isMaster) {
        List<ResponseDto> allResp = new ArrayList<ResponseDto>();
        ResponseDto resp = playService.playWithNumber(num, isMaster);
        allResp.add(resp);
        return allResp;
    }


}
