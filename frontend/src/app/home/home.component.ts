import {Component, OnInit, ViewChild} from '@angular/core';
import {ModalDirective} from 'ngx-bootstrap/modal';
import {Router} from "@angular/router";
import {VxgameserviceService} from "../vxgameservice.service";
import {Deck} from "../model/deck.interface";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @ViewChild('autoShownModal') public autoShownModal: ModalDirective;
  public isModalShown: boolean = false;

  ngOnInit(): void {
    this.getGame();
  }

  games: Deck[];
  playerName: string;
  hasError: boolean = false;

  constructor(private vxGameService: VxgameserviceService, private router: Router) {

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
      this.vxGameService.joinGame().subscribe(res => {
        console.log(res.json());
        this.hideModal();
        this.vxGameService.setGame(res.json());
        this.router.navigateByUrl('game');
      }, error => console.error(error));
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
