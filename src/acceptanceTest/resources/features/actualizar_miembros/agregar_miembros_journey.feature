# language: es

Característica: Editar miembros de grupo existente

  @journey
  Escenario: Agregar nuevo miembro a un grupo nuevo
    * existe el grupo #21 'Picnic en Palermo'
    * el usuario inicia la aplicación
    * el usuario selecciona editar miembros al grupo #21
    * agrega al usuario "javier"
    //podria meter una validacion que recibo mensaje OK o algo asi, para que contraste con el proximo test de que valide q no puedo sumar 2 veces al mismo o algo asi
    * deberia visualizar dentro del listado al grupo #21 con el miembro "javier"

