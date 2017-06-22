import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map"
import {Deck} from "./model/deck.interface";
import {PlayerDao} from "./model/dao/PlayerDao";

@Injectable()
export class VxgameserviceService {

  constructor(public http: Http) {
  }

  private game: Deck;
  private player: PlayerDao;

  public allGame(): Observable<any> {
    return this.http.get('http://localhost:8181/api/decks');
  }

  public joinGame(): Observable<any> {
    return this.http.post('http://localhost:8181/api/game/' + this.game.id + "/join-game", this.getPlayer());
  }

  public setGame(newGame): void {
    this.game = newGame
  }

  public getGame(): Deck {
    return this.game;
  }

  public newPlayer(playerName: string): void {
    this.player = new PlayerDao(playerName, this.game);
  }

  public getPlayer(): PlayerDao {
    return this.player;
  }

}
