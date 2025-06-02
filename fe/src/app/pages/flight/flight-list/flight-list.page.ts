import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {IonContent, IonHeader, IonText, IonTitle, IonToolbar} from '@ionic/angular/standalone';
import {IonicModule, IonModal} from "@ionic/angular";
import {FlightListItemView} from "../../../model/flight";
import {FlightService} from "../../../services/flight.service";

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.page.html',
  styleUrls: ['./flight-list.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonicModule]
})
export class FlightListPage implements OnInit {


  public flights: FlightListItemView[] = [];

  constructor(private flightService: FlightService) { }

  async ngOnInit() {
    this.flights = await this.flightService.findAll();
  }

  async createFlight(code: any, modal: IonModal) {

    const f = await this.flightService.create(code);
    this.flights.push(f);
    modal.dismiss();

  }
}
