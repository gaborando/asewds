import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'flight-list',
    loadComponent: () => import('./pages/flight/flight-list/flight-list.page').then( m => m.FlightListPage)
  },
];
