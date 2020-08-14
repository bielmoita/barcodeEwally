import { Component, OnInit } from '@angular/core';
import { LinhaService } from '../service/linha.service';
import { Linha } from '../model/Linha';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  linhaDig: Linha[];
  linhaVer: Linha = new Linha;

  constructor(private linhaService: LinhaService) { }

  ngOnInit() {
    this.findAllLinha()
  }


  findAllLinha(){
    this.linhaService.getAllLinhas().subscribe((resp: Linha[]) => {
      this.linhaDig = resp;
    })
  }

  verificar(){
    this.linhaService.postLinha(this.linhaVer).subscribe((resp: Linha) =>{
      this.linhaVer = resp;
      location.assign('/home')
    })
  }

}
