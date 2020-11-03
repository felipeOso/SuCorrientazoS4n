package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio.{A, Coordenada, D, I, N, Posicion}
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec

class ServicioEntregaTest extends AnyWordSpec{

  "Test ServicioEntrega" should{

    "Al invocar la funcion obtenerPosicionEntrega" when {
      "donde a partir de una entrega que es una lista de instrucciones y una posicion inicial" +
        "calcula y retorna la posicion final de la entrega" in{

        val posicionInicial = Posicion(Coordenada(0,0),N)
        val listaInstrucciones = List(A,I,A,A,D)
        val posicionFinalEsperada = Posicion(Coordenada(-2,1),N)

        val posicionFinal = ServicioEntrega.obtenerPosicionEntrega(listaInstrucciones, posicionInicial)

        posicionFinal mustBe posicionFinalEsperada
      }
    }
  }

}
