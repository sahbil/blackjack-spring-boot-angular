/**
 * Created by xiabili on 22/06/2017.
 */
export interface Hand {
  id: number;
  betAmount: number;
  finished: boolean;
  buts: boolean;
  blackJack: boolean;
  score: number
}
