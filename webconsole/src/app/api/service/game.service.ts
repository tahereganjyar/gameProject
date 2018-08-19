import {Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {ResponseDto} from "../dto/app.response-service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs/observable/of";

@Injectable()
export class GameService implements OnInit {


  private playUrl = 'api/game/play';
  private openSocketUrl = '/api/game/openSocket';

  constructor(private  http: HttpClient) {
  }


  ngOnInit() {
  }

  openSocketToConnection(): Observable<ResponseDto[]> {


    return this.http.get<ResponseDto[]>(this.openSocketUrl).pipe(catchError(this.handleError('openSocketToConnection', [])));


  }

  play(selectedNum: number, isMaster: boolean): Observable<ResponseDto[]> {


    return this.http.get<ResponseDto[]>(this.playUrl + '/' + selectedNum + '/' + isMaster).pipe(catchError(this.handleError('play', [])));

  }

  private handleError<T>(operation = 'operation', result?: T) {

    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };


  }


}
