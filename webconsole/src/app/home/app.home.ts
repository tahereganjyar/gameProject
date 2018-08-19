import {AfterViewInit, Component, OnInit} from "@angular/core";
import {GameService} from "../api/service/game.service";
import {ResponseDto} from "../api/dto/app.response-service";

@Component({

  templateUrl: 'app.home.html',
  providers: [GameService]

})
export class HomeComponent implements OnInit, AfterViewInit {

  playerTypeMsg = 'Please wait .....'
  anotherPlayerResp = '';
  current_turn = false;
  isMaster = true;

  constructor(private  categoryService: GameService) {


  }


  ngOnInit() {


    this.categoryService.openSocketToConnection().subscribe(cats => this.afterRecievedDataFromSocketToConnection(cats));
  }

  doPlay(data: any) {

    this.categoryService.play(data, this.isMaster).subscribe(items => this.afterPlay(items));
    this.current_turn = true;


  }

  afterRecievedDataFromSocketToConnection(result: ResponseDto[]) {
    this.playerTypeMsg = result[0].title;
    if (this.playerTypeMsg.endsWith('client')) {
      this.current_turn = true;
      this.isMaster = false;
      this.categoryService.play(-1, this.isMaster).subscribe(items => this.afterPlay(items));

    }

  }

  afterPlay(result: ResponseDto[]) {
    this.anotherPlayerResp = result[0].title;
    this.current_turn = false;

  }

  ngAfterViewInit() {


  }

}
