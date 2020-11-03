package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio.{A, Coordenada, N, Posicion}
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec

class ServicioPosicionTest extends AnyWordSpec{

  "Test ServicioPosicion" should {

    "Al invocar la funcion actualizarPosicion" when {
      "donde a partir de una instruccion modificara la posicion " +
        "debe retornar una nueva posicion" in {

        val posicion = Posicion(Coordenada(0,0),N)
        val instruccion = A
        val posicionEsperada = Posicion(Coordenada(0,1),N)

        val posicionActualizada= ServicioPosicion.actualizarPosicion(posicion,instruccion)

        posicionActualizada mustBe posicionEsperada

      }
    }
  }

}
