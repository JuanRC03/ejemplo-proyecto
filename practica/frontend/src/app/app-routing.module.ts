
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Guard
import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';

//Inicio
import { HomeComponent } from './pages/inicio/home/home.component';

//Admin
import { SidebarAdminComponent } from './pages/admin/sidebar-admin/sidebar-admin.component';
import { ListarClientesComponent } from './pages/admin/listar-clientes/listar-clientes.component';
import { ViewUsuariosComponent } from './pages/admin/view-usuarios/view-usuarios.component';

//Usuario
import { DashClienteComponent } from './pages/cliente/dash-cliente/dash-cliente.component';
import { PerfilComponent } from './pages/cliente/perfil/perfil.component';
import { SidebarUsuarioComponent } from './pages/usuario/sidebar-usuario/sidebar-usuario.component';
import { PerfilUsuarioComponent } from './pages/usuario/perfil-usuario/perfil-usuario.component';


const routes: Routes = [
  {
    path : '',
    component : HomeComponent,
    pathMatch : 'full'
  },
  
  {
    path:'admin',
    component:SidebarAdminComponent,
    canActivate:[AdminGuard],
    children:[
      {
        path : '',
        component : ViewUsuariosComponent
      },

    ]
  },
  {
    path:'empleado',
    component:SidebarUsuarioComponent,
    canActivate:[NormalGuard],
    children : [
      {
        path : '',
        component : PerfilUsuarioComponent
      },
      
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
