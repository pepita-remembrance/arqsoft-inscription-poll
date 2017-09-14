import { Component } from '@angular/core';
import CarrersService from './services/carrers.service';
import { OnInit } from '@angular/core';

import {Carrer} from './models/course'

@Component({
  selector: 'app-root',
  providers: [CarrersService],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app';
  carrers : Carrer[];

  ngOnInit(): void {
    this.getCarrers();
  }

  constructor(private carrersService: CarrersService) {}

  getCarrers() : void{
    this.carrersService.getCarrers().then(carrers => this.carrers = carrers);
  }
}
