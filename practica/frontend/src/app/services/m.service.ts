import { Injectable } from '@angular/core';

import baserUrl from './helper';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MService {

  constructor(private httpClient: HttpClient) { }

  public save(user:any){
    return this.httpClient.post(`${baserUrl}/m/save`,user);
  }

  public update(user:any){
    return this.httpClient.put(`${baserUrl}/m/update`,user);
  }
  
  public findById(id:any){
    return this.httpClient.get(`${baserUrl}/m/find-by-id/${id}`);
  }

  public findAll(){
    return this.httpClient.get(`${baserUrl}/m/find-all`);
  }

  public findAllByVigente(){
    return this.httpClient.get(`${baserUrl}/m/find-all-by-vigente`);
  }

  public findAllByNoVigente(){
    return this.httpClient.get(`${baserUrl}/m/find-all-by-no-vigente`);
  }

  public delete(id:any){
    return this.httpClient.delete(`${baserUrl}/m/delete/${id}`);
  }

  public restore(id:any){
    return this.httpClient.delete(`${baserUrl}/m/restore/${id}`);
  }

  public findAllPaginableByVigenciaBusqueda(page: number, size: number, busqueda1:any, vigencia:any){
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('busqueda1', busqueda1.toString())
      .set('vigencia', vigencia.toString());

    return this.httpClient.get(`${baserUrl}/m/find-all-paginable-by-vigencia-busqueda`, { params });
  }
}
