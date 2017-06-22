import {Deck} from "../deck.interface";
/**
 * Created by xiabili on 22/06/2017.
 */
export class PlayerDao {
  name: string;
  deck_id: number;
  deck: Deck;

  constructor(newName: string, deck: Deck) {
    this.name = newName;
    this.deck = deck;
  }
}
