import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { DatosService } from 'src/app/services/datos.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private datosService: DatosService,
    public dialog: MatDialog,
    public userService:UserService) { }

  ngOnInit(): void {
    this.listarDataPaginableVigente();
  }

  ngAfterViewInit(): void {
  }

  displayedColumns = ['dato1', 'dato2', 'dato3', 'dato4', 'dato5', 'opciones'];

  //Paginacion vigente
  pageSizeVigente: number = 5
  pageNumberVigente: number = 0
  totalClientesVigente: number = 0;
  pageSizeOptionsVigente = [5, 10, 20, 50, 100]
  handlePageVigente(e: PageEvent) {
    this.pageSizeVigente = e.pageSize
    this.pageNumberVigente = e.pageIndex
    this.listarDataPaginableVigente();
  }

  //Variables busqueda
  public search: string = '';
  public searchFecha: string = '';
  public searchFechaFin: string = '';

  //Buscar
  buscar(){
    this.listarDataPaginableVigente();
  }
  
  //Resetar busqueda
  resetear(){
    this.searchFecha='';
    this.searchFechaFin = ''
    this.search= '';
    this.listarDataPaginableVigente();
  }

  //Listar datos vigentes
  dataVigente: any = []
  listarDataPaginableVigente() {
    this.userService.findAllPaginableByVigenciaBusqueda(this.pageNumberVigente, this.pageSizeVigente,this.search, true).subscribe((res:any) => {
      this.dataVigente = res.content;
      this.totalClientesVigente = res.totalElements;
    },
      err => console.log(err)
    )
  }
}
//-----------------------------------------------------------