import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Grupo } from '../../model/grupo';
import { IdentificarGrupoPipe } from '../../pipes/identificar-grupo.pipe';
import { GrupoService } from '../../services/grupo.service';

@Component({
  selector: 'app-miembro-nuevo',
  templateUrl: './miembro-nuevo.component.html',
  styleUrls: ['./miembro-nuevo.component.css']
})
export class MiembroNuevoComponent implements OnInit {

  mostrar: boolean = false;

  grupo: Grupo ;

  monto: number = 0.0;

  @Output() readonly guardadoEvent = new EventEmitter<void>();

  constructor(
    private grupoService: GrupoService,
    private messageService: MessageService,
    private identificarGrupo: IdentificarGrupoPipe) {

  }

  ngOnInit(): void {

  }

  iniciarPara(grupo: Grupo): void {

    this.mostrar = true;
    this.grupo = grupo;
    this.monto = 0.0;
  }

  cancelar(): void {

    this.mostrar = false;
  }

  guardar(): void {

    this.grupoService.agregarGasto(this.grupo, this.monto).subscribe(
      grupo => this.guardadoExitoso(grupo),
      error => this.guardadoFallido(error)
    );
  }

  private guardadoExitoso(grupo: Grupo): void {

    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: `Gasto agregado al grupo '${this.identificarGrupo.transform(grupo)}'`,
    });
    this.guardadoEvent.emit();
    this.mostrar = false;
  }

  private guardadoFallido(error: any): void {

    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: error.mensaje,
    });
  }
}
