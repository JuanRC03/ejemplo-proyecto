import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DatosService } from 'src/app/services/datos.service';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-usuarios',
  templateUrl: './view-usuarios.component.html',
  styleUrls: ['./view-usuarios.component.css']
})
export class ViewUsuariosComponent implements OnInit {

  constructor(
    private datosService: DatosService,
    public dialog: MatDialog,
    public userService:UserService) { }

  ngOnInit(): void {
    this.listarDataPaginableVigente();
    this.listarDataPaginableNoVigente();
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

  //Paginacion no vigente
  pageSizeNoVigente: number = 5
  pageNumberNoVigente: number = 0
  totalClientesNoVigente: number = 0;
  pageSizeOptionsNoVigente = [5, 10, 20, 50, 100]
  handlePageNoVigente(e: PageEvent) {
    this.pageSizeNoVigente = e.pageSize
    this.pageNumberNoVigente = e.pageIndex
    this.listarDataPaginableNoVigente();
  }

  //Variables busqueda
  public search: string = '';
  public searchFecha: string = '';
  public searchFechaFin: string = '';

  //Buscar
  buscar(){
    this.listarDataPaginableNoVigente();
    this.listarDataPaginableVigente();
  }
  
  //Resetar busqueda
  resetear(){
    this.searchFecha='';
    this.searchFechaFin = ''
    this.search= '';
    this.listarDataPaginableNoVigente();
    this.listarDataPaginableVigente();
  }

  //Listar datos vigentes
  dataVigente: any = []
  listarDataPaginableVigente() {
    this.userService.findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndUsuario(this.pageNumberVigente, this.pageSizeVigente,this.search, true, this.searchFecha, this.searchFechaFin, 2).subscribe((res:any) => {
      this.dataVigente = res.content;
      this.totalClientesVigente = res.totalElements;
    },
      err => console.log(err)
    )
  }

  //Listar datos no vigentes
  dataNoVigente: any = []
  listarDataPaginableNoVigente() {
    this.userService.findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndUsuario(this.pageNumberVigente, this.pageSizeVigente,this.search, false, this.searchFecha, this.searchFechaFin, 2).subscribe((res:any) => {
      console.log(res)
      this.dataNoVigente = res.content;
      this.totalClientesNoVigente = res.totalElements;
    },
      err => console.log(err)
    )
  }

  //Abrir registrar datos
  registrarDatos(): void {
    const dialogRef = this.dialog.open(RegistroUsuario, {
    });
    dialogRef.afterClosed().subscribe(() => {
      this.listarDataPaginableVigente();
    });
  }

   //Abrir actualizar datos
   actualizarDatos(id:any): void {
    const dialogRef = this.dialog.open(ActualizarUsuario, {
      data: { idDato: id
      },
    });
    dialogRef.afterClosed().subscribe(() => {
      this.listarDataPaginableVigente();
    });
  }

  //Eliminar datos
  eliminar(id: any) {
    Swal.fire({
      title: 'Eliminar',
      text: '¿Estás seguro de eliminar?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.delete(id).subscribe(
          (data) => {
            Swal.fire('Eliminado', 'Se ha sido eliminado', 'success');
            this.listarDataPaginableNoVigente();
            this.listarDataPaginableVigente();
          },
          (error) => {
            Swal.fire('Error', 'Error al eliminar', 'error');
          }
        )
      }
    })
  }

  //Activar datos
  activar(id: any) {
    Swal.fire({
      title: 'Activar datos',
      text: '¿Estás seguro activar?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Activar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.restore(id).subscribe(
          (data) => {
            Swal.fire('Activada', 'Datos activados', 'success');
            this.listarDataPaginableNoVigente();
            this.listarDataPaginableVigente();
          },
          (error) => {
            Swal.fire('Error', 'Error al activar los datos', 'error');
          }
        )
      }
    })
  }

  /////////////////////////////////////////////////////////////////////////////////////
  //opcional eliminar

  //descarhar PDF
  descargarPdf() {
    this.userService.descargarUsuariosPDF().subscribe((blob: Blob) => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'usuarios.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  //Descargar excel
  descargarExcel() {
    this.userService.descargarUsuariosExcel().subscribe((blob: Blob) => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'usuarios.xlsx';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  //Modelo para seleccionar dato
  estadoSeleccionado = {
    idEstado: 0,
    nombreEstado: 'Todos',
  }

  //Edad minima y maxima para el filtro
  ageMinima = 0;
  ageMaxima = 100;
  ageError: boolean = false;
  /////////////////////////////////////////////////////////////////////////////////////

}
//-------------------------------------------------------------------------------

//-------------------------------------------------------------------------------
//Open dialog registrar
@Component({
  selector: 'registrar-usuario',
  templateUrl: 'registrar-usuario.html',
  styleUrls: ['./view-usuarios.component.css']
})
export class RegistroUsuario {
  constructor(
    public dialogRef: MatDialogRef<RegistroUsuario>,
    private loginService: LoginService,
    private router: Router,
    private snack: MatSnackBar,
    private dialog: MatDialog,
    private userService:UserService,
  ) { }

  ngOnInit(): void {
  }

  //Modelo
  public dataInput = {
    email : '',
    contrasenia : '',
    cedula : '',
    nombre : '',
    apellido : '',
    fechaNacimiento : '',
    creadoPor:'',
    rol: {
      idRol: 2
    }
  }

  //Obtener usuario actual
  userActual:any = null;

  //Enviar datos
  formSubmit(){

    if(this.dataInput.email == '' || this.dataInput.email == null){
      this.snack.open('El email es requerido !!','Aceptar',{
        duration : 3000,
        verticalPosition : 'top',
        horizontalPosition : 'right'
      });
      return;
    }

    if(this.dataInput.contrasenia == '' || this.dataInput.contrasenia == null){
      this.snack.open('La contraseña es requerido !!','Aceptar',{
        duration : 3000,
        verticalPosition : 'top',
        horizontalPosition : 'right'
      });
      return;
    }

    if(this.dataInput.cedula == '' || this.dataInput.cedula == null){
      this.snack.open('La cédula es requerido !!','Aceptar',{
        duration : 3000,
        verticalPosition : 'top',
        horizontalPosition : 'right'
      });
      return;
    }

    if(this.dataInput.nombre == '' || this.dataInput.nombre == null){
      this.snack.open('El nombre es requerido !!','Aceptar',{
        duration : 3000,
        verticalPosition : 'top',
        horizontalPosition : 'right'
      });
      return;
    }

    if(this.dataInput.apellido == '' || this.dataInput.apellido == null){
      this.snack.open('El apellido es requerido !!','Aceptar',{
        duration : 3000,
        verticalPosition : 'top',
        horizontalPosition : 'right'
      });
      return;
    }
    this.userActual = this.loginService.getUser();
    this.dataInput.creadoPor=this.userActual.nombre;
    this.userService.save(this.dataInput).subscribe(
      (data) => {
        console.log(data);
        Swal.fire('Registrado', 'Registro con exito', 'success');
        this.dialogRef.close();
      },(error) => {
        console.log(error);
        Swal.fire('Error', 'Error en el sistema', 'error');
      }
    )
  }

}
//-------------------------------------------------------------------------------

//-------------------------------------------------------------------------------
//Datos datos para open dialog
interface datosOpenDialog {
  idDato:0
}
//-------------------------------------------------------------------------------

//-------------------------------------------------------------------------------
//Open dialog actualizar
@Component({
  selector: 'actualizar-usuario',
  templateUrl: 'actualizar-usuario.html',
  styleUrls: ['./view-usuarios.component.css']
})
export class ActualizarUsuario {
  constructor(
    public dialogRef: MatDialogRef<ActualizarUsuario>,
    @Inject(MAT_DIALOG_DATA) public dataDialog: datosOpenDialog,
    private snack: MatSnackBar,
    private userService: UserService,
    private login:LoginService
  ) { }

  ngOnInit(): void {
    this.obtenerDatos() 
  }

  //Modelo
  public dataInput = {
    idUsuario:0,
    email : '',
    cedula:'',
    contrasenia:'',
    nombre : '',
    apellido : '',
    fechaNacimiento : null as any,
    actualizadoPor:'',
    vigencia:1,
    rol: {
      idRol: 2
    }
  }

  //Obtener usuario actual
  userActual:any = null;

  //Agregar datos a modelo
  obtenerDatos() : void {
    this.userActual = this.login.getUser();
    this.userService.findById(this.dataDialog.idDato).subscribe((res:any) => {
      this.dataInput.idUsuario=res.idUsuario;
      this.dataInput.email=res.email;
      this.dataInput.contrasenia=res.contrasenia;
      this.dataInput.cedula=res.cedula;
      this.dataInput.nombre=res.nombre;
      this.dataInput.apellido=res.apellido;
      this.dataInput.fechaNacimiento=new Date(res.fechaNacimiento).toISOString().substring(0, 10);
      this.dataInput.actualizadoPor=this.userActual.nombre;
    },
      err => console.log(err)
    )
  }

  //Enviar datos
  formSubmit() {
    this.userService.update(this.dataInput).subscribe(
      (data) => {
        Swal.fire('Actualizado', 'Actualizado con exito', 'success');
        this.dialogRef.close();

      }, (error) => {
        console.log(error);
        Swal.fire('Error', 'Error en el sistema', 'error');
      }
    )
  }
}
//-------------------------------------------------------------------------------

