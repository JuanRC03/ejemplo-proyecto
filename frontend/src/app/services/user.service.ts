import { Injectable } from '@angular/core';

import baserUrl from './helper';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(private httpClient: HttpClient) { }

    public save(user:any){
      return this.httpClient.post(`${baserUrl}/usuario/save`,user);
    }

    public update(user:any){
      return this.httpClient.put(`${baserUrl}/usuario/update`,user);
    }
    
    public findById(id:any){
      return this.httpClient.get(`${baserUrl}/usuario/find-by-id/${id}`);
    }

    public findAllPaginableByVigenciaBusqueda(page: number, size: number, busqueda1:any, vigencia:any){
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('busqueda1', busqueda1.toString())
        .set('vigencia', vigencia.toString());
  
      return this.httpClient.get(`${baserUrl}/usuario/find-all-paginable-by-vigencia-busqueda`, { params });
    }

    public findAllPaginableByVigenciaUsuarioFecha(page: number, size: number, busqueda1:any, vigencia:any, fecha:any, idRol:any){
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('busqueda1', busqueda1.toString())
        .set('vigencia', vigencia.toString())
        .set('fecha', fecha.toString())
        .set('idRol', idRol.toString());
  
      return this.httpClient.get(`${baserUrl}/usuario/find-all-paginable-by-vigencia-usuario-fecha-rol`, { params });
    }

    public findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndUsuario(page: number, size: number, busqueda1:any, vigencia:any, fechaInicio:any, fechaFin:any, idRol:any){
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('busqueda1', busqueda1.toString())
        .set('vigencia', vigencia.toString())
        .set('fechaInicio', fechaInicio.toString())
        .set('fechaFin', fechaFin.toString())
        .set('idRol', idRol.toString());
  
      return this.httpClient.get(`${baserUrl}/usuario/find-all-by-fecha-nacimiento-between-and-rol-idrol-and-vigencia-and-usuario`, { params });
    }

    public findAllByVigente(){
      return this.httpClient.get(`${baserUrl}/usuario/find-all-by-vigente`);
    }

    public findAllByNoVigente(){
      return this.httpClient.get(`${baserUrl}/usuario/find-all-by-no-vigente`);
    }

    public delete(id:any){
      return this.httpClient.delete(`${baserUrl}/usuario/delete/${id}`);
    }

    public restore(id:any){
      return this.httpClient.delete(`${baserUrl}/usuario/restore/${id}`);
    }

    public descargarUsuariosExcel(): Observable<Blob> {
      return this.httpClient.get(`${baserUrl}/usuario/excel`, { responseType: 'blob' });
    }

    public descargarUsuariosPDF(): Observable<Blob> {
      return this.httpClient.get(`${baserUrl}/usuario/pdf`, { responseType: 'blob' });
    }
}
