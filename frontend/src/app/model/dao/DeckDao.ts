import {Player} from "../player.interface";
/**
 * Created by xiabili on 22/06/2017.
 */
export class DeckDao {
  id: number;
  playerList: Player[];

  constructor(id: number, playerList) {
    this.id = id;
    this.playerList = playerList;
  }
}
