/**
 * Created by xiabili on 22/06/2017.
 */
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {BasicgameComponent} from "./basicgame/basicgame.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
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
