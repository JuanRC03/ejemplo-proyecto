import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { MatDrawer } from '@angular/material/sidenav';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sidebar-admin',
  templateUrl: './sidebar-admin.component.html',
  styleUrls: ['./sidebar-admin.component.css']
})
export class SidebarAdminComponent {

   isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
    public login: LoginService,
    private router: Router) { }

  //Menu desplegable
  @ViewChild('drawer') drawer!: MatDrawer;
  menuVisible = true;
  botonMenuVisible = true;
  toggleBoton() {
    this.botonMenuVisible = false;
    this.menuVisible = true;
  }

  ngOnInit(): void {
  }

  //Cerrar sesión
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

  //Navegar a inicio
  navegarPagina() {
    this.router.navigate(['/']).then(() => {
      window.location.reload();
    });
  }

}
