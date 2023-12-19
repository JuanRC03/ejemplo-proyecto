import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authInterceptorProviders } from './services/auth.interceptor';
import { NgxUiLoaderModule , NgxUiLoaderHttpModule } from "ngx-ui-loader";
import { PaginatePipe } from './pipes/paginate.pipe';

//Material
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatSelectModule} from '@angular/material/select';
import { MatSlideToggleModule} from '@angular/material/slide-toggle';
import { MatButtonModule} from '@angular/material/button';
import { MatListModule} from '@angular/material/list';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule} from '@angular/material/icon';
import { MatPaginatorModule} from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule} from '@angular/material/dialog';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule} from '@angular/material/core';
import { MatSliderModule} from '@angular/material/slider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule} from '@angular/material/menu';
import { MatGridListModule} from '@angular/material/grid-list';
import { MatTabsModule} from '@angular/material/tabs';

//Admin
import { SidebarAdminComponent } from './pages/admin/sidebar-admin/sidebar-admin.component';
import { RegistrosDatos } from './pages/admin/listar-clientes/listar-clientes.component';
import { dialogRegistro } from './pages/admin/listar-clientes/listar-clientes.component';
import { EditarUsuario } from './pages/admin/listar-clientes/listar-clientes.component';
import { VerPremioAmin } from './pages/admin/listar-clientes/listar-clientes.component';
import { ListarClientesComponent } from './pages/admin/listar-clientes/listar-clientes.component';
import { ViewUsuariosComponent } from './pages/admin/view-usuarios/view-usuarios.component';
import { RegistroUsuario } from './pages/admin/view-usuarios/view-usuarios.component';
import { ActualizarUsuario } from './pages/admin/view-usuarios/view-usuarios.component';

//Inicio
import { Autenticacion } from './pages/inicio/navbar-inicio/navbar-inicio.component';
import { NavbarInicioComponent } from './pages/inicio/navbar-inicio/navbar-inicio.component';
import { HomeComponent } from './pages/inicio/home/home.component';


//Usuario
import { PerfilComponent } from './pages/cliente/perfil/perfil.component';
import { VerPremioUsuario } from './pages/cliente/perfil/perfil.component';
import { DashClienteComponent } from './pages/cliente/dash-cliente/dash-cliente.component';
import { VerPremio } from './pages/cliente/perfil/perfil.component';
import { SidebarUsuarioComponent } from './pages/usuario/sidebar-usuario/sidebar-usuario.component';
import { PerfilUsuarioComponent } from './pages/usuario/perfil-usuario/perfil-usuario.component';




@NgModule({
  declarations: [
    AppComponent,
    ListarClientesComponent,
    PaginatePipe,
    DashClienteComponent,
    PerfilComponent,
    dialogRegistro,
    EditarUsuario,
    RegistrosDatos,
    VerPremio,
    VerPremioAmin,
    VerPremioUsuario,
    ViewUsuariosComponent,
    RegistroUsuario,
    ActualizarUsuario,
    SidebarAdminComponent,
    Autenticacion,
    NavbarInicioComponent,
    HomeComponent,
    SidebarUsuarioComponent,
    PerfilUsuarioComponent

  ],
  imports: [
    MatSliderModule,
    MatPaginatorModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    NgxUiLoaderModule,
    MatTableModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatTabsModule,
    MatGridListModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true
    }),
    LayoutModule,
    MatSidenavModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
