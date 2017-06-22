import {Component, OnInit} from '@angular/core';
import {VxgameserviceService} from "../vxgameservice.service";
import {Router} from "@angular/router";
import {Player} from "../model/player.interface";
import {DeckDao} from "../model/dao/DeckDao";

@Component({
  selector: 'app-basicgame',
  templateUrl: './basicgame.component.html',
  styleUrls: ['./basicgame.component.css']
})
export class BasicgameComponent implements OnInit {

  game: DeckDao;
  players: Player[];

  constructor(private vxGameService: VxgameserviceService, private router: Router) {
  }

  ngOnInit() {
    this.game = this.vxGameService.getGame();
    if (!this.game) {
      this.router.navigateByUrl('');
    }
  }

}
