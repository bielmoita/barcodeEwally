import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Linha } from '../model/Linha';

@Injectable({
  providedIn: 'root'
})
export class LinhaService {

  constructor(private http: HttpClient) { }

  //CRUD - Vou fazer o Create e o READ - fazer posteriormente o Update e Delete

  getAllLinhas(){
    return this.http.get('http://localhost:8080/codigobarras');
  }

  postLinha(linha: Linha){
    return this.http.post('http://localhost:8080/codigobarras/', linha);
  }




}
