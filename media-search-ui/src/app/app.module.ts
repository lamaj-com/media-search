import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './_shared/material.module';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AppConfigModule } from './_utilities/app.config';
import { HttpClientModule } from '@angular/common/http';
import { SearchComponent } from './_components/search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    AppConfigModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
