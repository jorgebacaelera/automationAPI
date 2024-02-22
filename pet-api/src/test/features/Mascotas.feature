# language: es
Característica: Gestionar sistema veterinaria

  Como dueño de la veterinaria
  Queremos realizar la creación y actualización de estados de un cupón de pago
  Para asegurar el correcto flujo de pago en efectivo

  # Escenarios de negocio
  @CrearMascota
  Esquema del escenario: Realizar registro de una mascota en el sistema

    Dado que una persona realiza una adopción de una mascota con nombre <NOMBRE>

    Y el dueño se registra en el sistema de la veterinaria con correo <CORREO>

    Cuando el cliente desea actualizar el nombre <NUEVO_NOMBRE> de su mascota

    Y el dueño desea actualizar su correo <NUEVO_CORREO>

    Entonces el sistema Pet deberá actualizar la información correctamente

    Y la veterinaria deberá eliminar el registro de la mascota y del dueño

    Ejemplos:
      | NOMBRE | NUEVO_NOMBRE | CORREO                    | NUEVO_CORREO                |
      | Fox    | Jorginho     | jorgebacaelera1@gmail.com | jorgebacaelera123@gmail.com |