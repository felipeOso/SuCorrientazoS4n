package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio.{Instruccion, Posicion}

import scala.annotation.tailrec

trait ServicioDomicilio {

  @tailrec
  final def entregar(listaInstrucciones:List[Instruccion], posicionInicial:Posicion ): Posicion = {
    listaInstrucciones match {
      case Nil => posicionInicial
      case lista => {
        val posicion = ServicioPosicion.actualizarPosicion(posicionInicial, lista.head)
        entregar(lista.tail, posicion)
      }
    }
  }
}
 object ServicioDomicilio extends ServicioDomicilio
