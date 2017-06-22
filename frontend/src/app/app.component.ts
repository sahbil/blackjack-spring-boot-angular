import {Component, OnInit, ViewChild} from '@angular/core';
import {VxgameserviceService} from "./vxgameservice.service";
import {ModalDirective} from 'ngx-bootstrap/modal';
import {Deck} from "./model/deck.interface";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('autoShownModal') public autoShownModal: ModalDirective;
  public isModalShown: boolean = false;

  ngOnInit(): void {
    this.getGame();
  }

  games: Deck[];
  playerName: string;
  hasError: boolean = false;

  constructor(public vxGameService: VxgameserviceService) {

  }

  getGame() {
    this.vxGameService.allGame().subscribe(
      res => {
        this.games = res.json();
      },
      error => console.log(error)
    );
  }

  public joinGame(): void {
    if (this.playerName) {
      this.hasError = false;
      this.vxGameService.newPlayer(this.playerName);
      this.vxGameService.joinGame().subscribe(res => console.log(res), error => console.error(error));
    } else {
      this.hasError = false;
    }
  }

  public showModal(game): void {
    this.vxGameService.setGame(game);
    this.isModalShown = true;
  }

  public hideModal(): void {
    this.autoShownModal.hide();
  }

  public onHidden(): void {
    this.isModalShown = false;
  }
}
