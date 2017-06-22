/**
 * Created by xiabili on 22/06/2017.
 */
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {BasicgameComponent} from "./basicgame/basicgame.component";

const routes: Routes = [
  {
    path: '',
    children: []
  },
  {
    path: 'game', component: BasicgameComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
