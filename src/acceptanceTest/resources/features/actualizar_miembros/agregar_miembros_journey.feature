# language: es

Característica: Editar miembros de grupo existente

  @journey
  Escenario: Agregar nuevo miembro a un grupo nuevo
    * existe el grupo #21 'Picnic en Palermo'
    * el usuario inicia la aplicación
    * el usuario selecciona editar miembros al grupo #21
    * agrega al usuario "javier"
    * guarda los cambios
    * ve la confirmación 'miembros actualizados'
    * ve que los miembros del grupo #42 incluye a "javier"

