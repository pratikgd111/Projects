import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { rejects } from 'assert';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor( ) { }

  getLocatioService():Promise<any>{
    return new Promise((resolve,reject)=>{
      navigator.geolocation.getCurrentPosition(resp=>{
        resolve({long: resp.coords.longitude, lat: resp.coords.latitude})
      })
    })
  }
}
