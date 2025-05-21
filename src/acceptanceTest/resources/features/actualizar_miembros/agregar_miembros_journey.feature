# language: es

Característica: Editar miembros de grupo existente

  @journey
  Escenario: Agregar nuevo miembro a un grupo nuevo
    * existe el grupo #21 'Picnic en Palermo'
    * el usuario inicia la aplicación
    * el usuario selecciona editar miembros al grupo #21
    * agrega al usuario "javier"
    * deberia visualizar dentro del listado al grupo #21 con el miembro "javier"


  @journey
  Escenario: No puedo agregar miembro ya existente a un grupo nuevo
    * existe el grupo #21 'Picnic en Palermo'
    * el usuario inicia la aplicación
    * el usuario selecciona editar miembros al grupo #21
    * agrega al usuario "raul"
    * el usuario selecciona editar miembros al grupo #21
    * agrega al usuario "raul"
    * debería ser informado que no puede realizar esa acción
    * deberia visualizar dentro del listado al grupo #21 con el miembro "raul" una sola vez