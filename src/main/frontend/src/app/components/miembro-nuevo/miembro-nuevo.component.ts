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
  id: number | undefined = 0;
  monto: number = 0.0;
  nombre: string = '';
  miembros: string[] = [];

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
    this.id = grupo.id
    this.nombre = grupo.nombre
    this.miembros = [...grupo.miembros]
  }

  cancelar(): void {

    this.mostrar = false;
  }

  guardar(): void {

    this.grupoService.actualizar(this.grupo, this.miembros).subscribe(
      grupo => this.guardadoExitoso(grupo),
      error => this.guardadoFallido(error)
    );
  }

  private guardadoExitoso(grupo: Grupo): void {

    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: `Miembros editados del grupo '${this.identificarGrupo.transform(grupo)}'`,
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
