import {Hand} from "./hand.interface";
export interface Player {
  id: number;
  name: string;
  cash: number;
  dealer: boolean;
  hands: Hand[];
}
