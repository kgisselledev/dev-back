import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) { }

  getClientsList(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(`${this.baseUrl}/clientes`);
  }

  getClientById(id: number): Observable<Client> {
    return this.httpClient.get<Client>(`${this.baseUrl}/${id}`);
  }

  createClient(client: Client): Observable<Client> {
    return this.httpClient.post<Client>(`${this.baseUrl}/cliente`, client);
  }

  updateClient(id: number, client: Client): Observable<Client> {
    return this.httpClient.put<Client>(`${this.baseUrl}/cliente/${id}`, client);
  }

  deleteClient(id: number): Observable<Object> {
    return this.httpClient.delete<Object>(`${this.baseUrl}/cliente/${id}`);
  }
}
