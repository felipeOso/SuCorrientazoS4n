package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio.{Instruccion, Posicion}

import scala.annotation.tailrec

trait ServicioEntrega {

  @tailrec
  final def obtenerPosicionEntrega(listaInstrucciones:List[Instruccion], posicionInicial:Posicion ): Posicion = {
    listaInstrucciones match {
      case Nil => posicionInicial
      case listaInst => {
        val posicion = ServicioPosicion.actualizarPosicion(posicionInicial, listaInst.head)
        obtenerPosicionEntrega(listaInst.tail, posicion)
      }
    }
  }
}
 object ServicioEntrega extends ServicioEntrega
