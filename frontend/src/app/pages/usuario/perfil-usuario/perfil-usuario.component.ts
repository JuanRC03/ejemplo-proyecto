import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DatosService } from 'src/app/services/datos.service';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit {

  constructor(private router: Router,
    public login: LoginService,
    public userService: UserService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.obtenerUsuario();
  }

  //Modelo
  public dataInput = {
    idUsuario: 0,
    email: '',
    cedula: '',
    contrasenia: '',
    nombre: '',
    apellido: '',
    fechaNacimiento: null as any,
    actualizadoPor: '',
    vigencia: 1,
    rol: {
      idRol: 2
    }
  }

  //Obtener usuario actual
  userActual: any = null;

  //Datos en modelo
  obtenerUsuario(): void {
    this.userActual = this.login.getUser();
    this.userService.findById(this.userActual.idUsuario).subscribe((res: any) => {
      this.dataInput.idUsuario = res.idUsuario;
      this.dataInput.email = res.email;
      this.dataInput.contrasenia = res.contrasenia;
      this.dataInput.cedula = res.cedula;
      this.dataInput.nombre = res.nombre;
      this.dataInput.apellido = res.apellido;
      this.dataInput.fechaNacimiento = new Date(res.fechaNacimiento).toISOString().substring(0, 10);
    },
      err => console.log(err)
    )
  }

  //Actualizar datos  
  actualizarDatos() {
    this.userService.update(this.dataInput).subscribe(
      (data) => {
        Swal.fire('Actualizado', 'Actualizado con exito', 'success');

      }, (error) => {
        console.log(error);
        Swal.fire('Error', 'Error en el sistema', 'error');
      }
    )
  }

}
