import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DatosService } from 'src/app/services/datos.service';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  constructor(private router: Router,
    public login: LoginService,
    public userService:UserService,
    public datosService: DatosService,
    public dialog: MatDialog,
    ) { }
    user:any = null;

    ngOnInit(): void {
      this.user = this.login.getUser();
      this.dataRegistro.usuario.idUsuario=this.user.idUsuario;
      this.obtenerUsuario() ;
    }

    registrosData: any = []
    validador=true;
    

    openPremio(title:any, description:any, thumbnail:any,price:any ): void {
      const dialogRef = this.dialog.open(VerPremio, {
        data: { title:title,
          description:description,
          thumbnail:thumbnail,
          price:price
        },
      });
      dialogRef.afterClosed().subscribe(() => {
        
      });
    }

    openPremioUsuario(): void {
      const dialogRef = this.dialog.open(VerPremioUsuario, {
      });
    }
  
  

  navegarPagina() {
    this.router.navigate(['/']).then(() => {
      window.location.reload();
    });
  }

  public logout() {
    this.login.logout();
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'La sesión se a cerrado con exito',
      showConfirmButton: false,
      timer: 3000
    })
    setTimeout(() => {
      this.navegarPagina()
    }, 3000);
  }

  crearRegistro() {
    Swal.fire({
      title: 'Iniciar turno',
      text: '¿Estás seguro de registrar entrada?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Ingresar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.dataRegistro.idRegistro='';
      }
    })
  }


  finalizarRegistro(id: any) {
    Swal.fire({
      title: 'Terminar turno',
      text: '¿Estás seguro registrar su salida?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Egresar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      this.dataRegistro.idRegistro=id;
      if (result.isConfirmed) {
        
      }
    })
  }

  public dataRegistro = {
    idRegistro:'',
    ingreso : '',
    salida : '',
    usuario: {
      idUsuario:0 
    }
  }

  public userData = {
    idUsuario:0,
    email : '',
    cedula : '',
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

  userDatos:any = null;
  userAdmin:any = null;

  obtenerUsuario() : void {
    this.userAdmin = this.login.getUser();
    this.userService.findById(this.userAdmin.idUsuario).subscribe(res => {
      this.userDatos = res;
      this.userData.idUsuario=this.userDatos.idUsuario;
      this.userData.email=this.userDatos.email;
      this.userData.contrasenia=this.userDatos.contrasenia;
      this.userData.cedula=this.userDatos.cedula;
      this.userData.nombre=this.userDatos.nombre;
      this.userData.apellido=this.userDatos.apellido;
      this.userData.fechaNacimiento=new Date(this.userDatos.fechaNacimiento).toISOString().substring(0, 10);
    },
      err => console.log(err)
    )
  }
 
  registroDatos() {
    this.userData.actualizadoPor=this.userDatos.usuario;
    this.userService.update(this.userData).subscribe(
      (data) => {
        Swal.fire('Actualizado', 'Actualizado con exito', 'success');

      }, (error) => {
        console.log(error);
        Swal.fire('Error al actualizar ', 'No se actualizo', 'error');
      }
    )
  }

  formatDate(date: string): string {
    const parts = date.split('-');
    if (parts.length === 3) {
      const [day, month, year] = parts;
      return `${year}-${month}-${day}`;
    }
    return '';
  }

}


interface datosPremio {
  title:'';
  description:'';
  thumbnail:'';
  price:'';
}

@Component({
  selector: 'view-premio',
  templateUrl: 'view-premio.html',
  styleUrls: ['./perfil.component.css']
})
export class VerPremio {
  constructor(
    
    public dialogRef: MatDialogRef<VerPremio>,
    @Inject(MAT_DIALOG_DATA) public datos: datosPremio,
    private snack: MatSnackBar,
    private userService: UserService,
    private login:LoginService,
  ) { }


  ngOnInit(): void {
    this.registrarPremio();
  }

  user:any = null;

  public premio = {
    fechaGano : '',
    fechaReclamo:'',
    descripcion:'',
    premio : '',
    usuario: {
      idUsuario: 0
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  registrarPremio() {
    this.user = this.login.getUser();
    this.premio.usuario.idUsuario=this.user.idUsuario;
    this.premio.descripcion=this.datos.description;
    this.premio.premio=this.datos.title;
  }

}

@Component({
  selector: 'view-premio-usuario',
  templateUrl: 'view-premio-usuario.html',
  styleUrls: ['./perfil.component.css']
})
export class VerPremioUsuario {
  constructor(
    
    public dialogRef: MatDialogRef<VerPremioUsuario>,
    private snack: MatSnackBar,
    private userService: UserService,
    private login:LoginService,
  ) { }


  ngOnInit(): void {
    this.user = this.login.getUser();
    this.premio.usuario.idUsuario=this.user.idUsuario;
    this.listarPremio();
    this.listarPremioReclamados();
  }

  user:any = null;

  displayedColumns = ['dato1', 'dato2'];
  displayedColumnsReclamado = ['dato1'];

  public premio = {
    idPremio:0,
    fechaGano : '',
    fechaReclamo:'',
    descripcion:'',
    premio : '',
    vigencia:0,
    usuario: {
      idUsuario: 0
    }
  }

  premiosData: any = []

  listarPremio() {
    /*this.premioService.obtenerPorUsuario(this.user.idUsuario).subscribe(res => {
      this.premiosData = res;
    },
      err => console.log(err)
    )*/
  }

  premiosDataReclamados: any = []
  listarPremioReclamados() {
    
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  registrarPremio(idPremio:any,premio:any,descripcion:any,fechaGano:any) {
    this.premio.idPremio=idPremio;
    this.premio.descripcion=descripcion;
    this.premio.premio=premio;
    this.premio.fechaGano=fechaGano;
  }

  //paginacion y busqueda
  page_size: number = 5
  page_number: number = 1
  page_size_options = [5, 10, 20, 50, 100]
  public search: string = '';
  public searchFecha: any = null;

  //Paginador
  handlePage(e: PageEvent) {
    this.page_size = e.pageSize
    this.page_number = e.pageIndex + 1
  }


}