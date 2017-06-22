import { Component, OnInit } from '@angular/core';
import {VxgameserviceService} from "../vxgameservice.service";
import {Router} from "@angular/router";
import {Deck} from "../model/deck.interface";
import {Player} from "../model/player.interface";

@Component({
  selector: 'app-basicgame',
  templateUrl: './basicgame.component.html',
  styleUrls: ['./basicgame.component.css']
})
export class BasicgameComponent implements OnInit {

  game: Deck;
  players: Player[];

  constructor(private vxGameService: VxgameserviceService, private router: Router) { }

  ngOnInit() {
    this.game = this.vxGameService.getGame();
  }

}
