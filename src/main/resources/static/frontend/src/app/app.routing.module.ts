import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { ClientListComponent } from "./client-list/client-list.component";
import { CreateClientComponent } from "./create-client/create-client.component";
import { UpdateClientComponent } from "./update-client/update-client.component";
import { DetailClientComponent } from "./detail-client/detail-client.component";

export const routes: Routes = [
    { path: 'clients', component: ClientListComponent },
    { path: 'create-client', component: CreateClientComponent },
    { path: '', redirectTo: 'clients', pathMatch: 'full' },
    { path: 'update-client/:id', component: UpdateClientComponent },
    { path: 'detail-client/:id', component: DetailClientComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],                                                                                                                                                                                                                                                                                                          
  exports: [RouterModule]
})
export class AppRoutingModule { }
