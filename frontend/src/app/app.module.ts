import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule, ButtonsModule, BsDropdownModule, ModalModule, SortableModule, TooltipModule} from 'ngx-bootstrap';
import {AppComponent} from './app.component';
import {BasicgameComponent} from './basicgame/basicgame.component';
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {VxgameserviceService} from "./vxgameservice.service";

@NgModule({
  declarations: [
    AppComponent,
    BasicgameComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AlertModule.forRoot(),
    ButtonsModule.forRoot(),
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    TooltipModule.forRoot(),
    SortableModule.forRoot()
  ],
  providers: [VxgameserviceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
