import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baserUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class DatosService {

  constructor(private httpClient: HttpClient) { 
    
  }

  public obtenerDatosBack(){
    return this.httpClient.get(`${baserUrl}/api/external-data`);
  }

}
