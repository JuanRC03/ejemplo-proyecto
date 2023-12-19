import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-navbar-inicio',
  templateUrl: './navbar-inicio.component.html',
  styleUrls: ['./navbar-inicio.component.css']
})
export class NavbarInicioComponent implements OnInit {

  isLoggedIn = false;
  user:any = null;

  constructor(public login:LoginService,
    public router:Router,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
      }
    )
  }

  //cerrar sesión
  public logout(){
    this.login.logout();
    window.location.reload();
  }

 


  public panelContro() {
    if (this.login.getUserRole() == 'ADMINISTRADOR') {
      this.router.navigate(['admin']);
    }
    else if (this.login.getUserRole() == 'EMPLEADO') {
      this.router.navigate(['empleado']);
    }
    else {
      this.login.logout();
    }
  }
  
  openDialogAutenticacion(): void {
    const dialogRef = this.dialog.open(Autenticacion);
  }
}


//-----------------------------------------------------------
//Autenticar
@Component({
  selector: 'autenticacion',
  templateUrl: 'autenticacion.html',
  styleUrls: ['./navbar-inicio.component.css']
})
export class Autenticacion {
  constructor(
    public dialogRef: MatDialogRef<Autenticacion>,
    private loginService: LoginService,
    private router: Router,
    private snack: MatSnackBar,
    public dialog: MatDialog,
    
  ) { }

  hidePass = true;

  loginData = {
    "email": '',
    "contrasenia": '',
  }

  acceso = {
    usuario: {
      idUsuario: 0
    }
  }

  datosUsuario: any;

  ngOnInit(): void {
  }

  user:any = null;

  formSubmit() {
    if (this.loginData.email.trim() == '' || this.loginData.email.trim() == null) {
      this.snack.open('El email es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    if (this.loginData.contrasenia.trim() == '' || this.loginData.contrasenia.trim() == null) {
      this.snack.open('La contraseña es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        this.loginService.loginUser(data.token);
        this.loginService.getCurrentUser().subscribe((user: any) => {
          this.dialogRef.close();
          this.loginService.setUser(user);
          this.datosUsuario = user;
          this.acceso.usuario.idUsuario = this.datosUsuario.idUsuario;
          if (this.loginService.getUserRole() == 'ADMINISTRADOR') {

            this.router.navigate(['admin']);
            this.loginService.loginStatusSubjec.next(true);
            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido administrador',
              showConfirmButton: false,
              timer: 3000
            })
          }
          else if (this.loginService.getUserRole() == 'EMPLEADO') {
            
            this.router.navigate(['empleado']);
            this.loginService.loginStatusSubjec.next(true);
            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido usuario',
              showConfirmButton: false,
              timer: 3000
            })

          } else {
            this.loginService.logout();
          }
        })
      }, (error) => {
        console.log(error);
        this.snack.open('Detalles inválidos , vuelva a intentar !!', 'Aceptar', {
          duration: 3000
        })
      }
    )
  }

  
  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

}
//-----------------------------------------------------------
