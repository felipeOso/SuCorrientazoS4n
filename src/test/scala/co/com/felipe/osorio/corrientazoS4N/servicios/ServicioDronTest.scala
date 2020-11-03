package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio._
import co.com.felipe.osorio.corrientazoS4N.util.Utils.waitForFutureResult
import monix.execution.Scheduler.Implicits.global
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec


class ServicioDronTest  extends AnyWordSpec {

  "Test ServicioDron" should {

    "Al realizar la ruta" when {
      "las instrucciones no superan 10 cuadras a la redonda, " +
        "debe ser posible realizar la ruta y retornar la posicion donde finalizo" in{

        val resultadoDronEsperado = Right(Dron("01",Posicion(Coordenada(-2,4),O),Ruta(Nil),List(Posicion(Coordenada(-2,4),O))))
        val dron = Dron("01",Posicion(Coordenada(0,0),N),Ruta(List(Entrega(List(A,A,A,A,I,A,A)))),Nil)

        val resultadoDron= waitForFutureResult(ServicioDron.realizarRuta(dron).value.runToFuture)

        resultadoDron mustBe resultadoDronEsperado
      }
    }


    "Al simular la ruta" when {
      "-donde a partir de la ruta y posicion inicial del dron" +
        "calcula la ruta y devuelve la posicion final de simular realizar la ruta" in{

        val ruta = Ruta(List(Entrega(List(A,A,A,A,I,A))))
        val listaPosicionesEsperadas = Right(List(Posicion(Coordenada(-1,4),O)))

        val listaPosiciones= waitForFutureResult(ServicioDron.simularRuta(ruta,Posicion(Coordenada(0,0),N)).value.runToFuture)

        listaPosiciones mustBe listaPosicionesEsperadas
      }
    }


  }

}
