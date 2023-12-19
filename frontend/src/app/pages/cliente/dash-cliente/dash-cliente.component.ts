import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dash-cliente',
  templateUrl: './dash-cliente.component.html',
  styleUrls: ['./dash-cliente.component.css']
})
export class DashClienteComponent implements OnInit {

  constructor(private router: Router,
    public login: LoginService,
    public userService:UserService) { }
    user:any = null;

    ngOnInit(): void {
      this.user = this.login.getUser();
      this.obtenerUsuario() 
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

  public userData = {
    idUsuario:0,
    email : '',
    contrasenia : '',
    nombreUsuario : '',
    apellidoUsuario : '',
    edad : 0,
    fechaNacimiento : null as any,
    fechaCreacion:'',
    fechaActualizacion:'',
    vigencia:1,
    rol: {
      idRol: 2
    }
  }

  userDatos:any = null;

  obtenerUsuario() {
    this.userService.findById(this.user.idUsuario).subscribe(res => {
      this.userDatos = res;
      this.user.idUsuario=this.userDatos.idUsuario;
      this.user.email=this.userDatos.email;
      this.user.contrasenia=this.userDatos.contrasenia;
      this.user.nombreUsuario=this.userDatos.nombreUsuario;
      this.user.apellidoUsuario=this.userDatos.apellidoUsuario;
      this.user.edad=this.userDatos.edad;
      this.user.fechaNacimiento=new Date(this.userDatos.fechaNacimiento).toISOString().substring(0, 10);
      this.user.fechaCreacion=this.userDatos.fechaCreacion;
      this.user.fechaActualizacion=this.userDatos.fechaActualizacion;
    },
      err => console.log(err)
    )
  }

 
  formSubmit() {
    
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
