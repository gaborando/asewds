import { Injectable } from '@angular/core';
import {FlightListItemView, FlightView} from "../model/flight";

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor() { }

  async findAll(): Promise<FlightListItemView[]> {
    return fetch('http://localhost:8080/api/flight/')
      .then(response => response.json());
  }

  async create(code: any): Promise<FlightView> {
    return {
      code
    }
  }
}
