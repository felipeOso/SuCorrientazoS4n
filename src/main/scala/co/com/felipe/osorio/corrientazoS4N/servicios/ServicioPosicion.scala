package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio._


sealed trait ServicioPosicion {
  def actualizarPosicion(posicionInicial:Posicion, instruccion:Instruccion): Posicion= {
    instruccion match {
      case A => posicionInicial.orientacion match {
        case O =>posicionInicial.copy(coordenada = Coordenada(posicionInicial.coordenada.posicionX -1, posicionInicial.coordenada.posicionY) )
        case E => posicionInicial.copy(coordenada = Coordenada(posicionInicial.coordenada.posicionX +1, posicionInicial.coordenada.posicionY) )
        case N => posicionInicial.copy(coordenada = Coordenada(posicionInicial.coordenada.posicionX, posicionInicial.coordenada.posicionY +1) )
        case S => posicionInicial.copy(coordenada = Coordenada(posicionInicial.coordenada.posicionX, posicionInicial.coordenada.posicionY -1) )

      }
      case I => posicionInicial.orientacion match {
        case O => posicionInicial.copy(orientacion = S )
        case E => posicionInicial.copy(orientacion = N )
        case N => posicionInicial.copy(orientacion = O )
        case S => posicionInicial.copy(orientacion = E )

      }
      case D => posicionInicial.orientacion match {
        case O => posicionInicial.copy(orientacion = N )
        case E => posicionInicial.copy(orientacion = S )
        case N => posicionInicial.copy(orientacion = E )
        case S => posicionInicial.copy(orientacion = O )

      }
    }
  }

}

object ServicioPosicion extends ServicioPosicion
