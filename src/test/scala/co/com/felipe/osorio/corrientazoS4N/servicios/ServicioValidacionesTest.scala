package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.constantes.Constantes
import co.com.felipe.osorio.corrientazoS4N.dominio._
import co.com.felipe.osorio.corrientazoS4N.util.Utils.waitForFutureResult
import monix.execution.Scheduler.Implicits.global
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec

class ServicioValidacionesTest extends AnyWordSpec {

  "Test ServicioValidaciones" should {

    "Al invocar la funcion validarRuta" when {
      "-la lista de domicilios es mayor a 3," +
        "debe retornar  error en la validacion e indicar una descripcion del error" in {

        val ruta = Ruta(List(Entrega(List(A, I, A, A)), Entrega(List(A, I, A, A, D, A)), Entrega(List(A, I, A, A, D, A)), Entrega(List(A, I, A, A, D, A))))
        val resultadoErrorEsperado = Left(ErrorServicio(Negocio, s"El numero maximo de entregas son: ${Constantes.ReglasNegocio.NumeroMaximoEntregas}"))
        val posicion = Posicion(Coordenada(2, 4), N)

        val resultadoValidacion = waitForFutureResult(ServicioValidaciones.validarRuta(ruta, posicion).value.runToFuture)

        resultadoValidacion.isLeft mustBe true
        resultadoValidacion.leftSideValue mustBe resultadoErrorEsperado

      }
    }

    "Al invocar la funcion validarRuta" when {
      "-la lista de domicilios es menor a 4," +
        "-al simular la ruta las coordenadas se encuentran dentro de la zona permitida" +
        "retorna  exito en la vaidacion" in {

        val ruta = Ruta(List(Entrega(List(A, I, A, A, D))))
        val posicion = Posicion(Coordenada(2, 4), N)

        val resultadoValidacion = waitForFutureResult(ServicioValidaciones.validarRuta(ruta, posicion).value.runToFuture)

        resultadoValidacion.isRight mustBe true
        resultadoValidacion.right.get mustBe true

      }
    }
    "Al invocar la funcion validarRuta" when {
      "-la lista de domicilios es menor a 4," +
        "-al simular la ruta las coordenadas No se encuentran dentro de la zona permitida" +
        "retorna  error en la vaidacion con su descripcion" in {

        val ruta = Ruta(List(Entrega(List(A, I, A, A, A, A, A, A, A, A, A, A, A, A, A, D))))
        val resultadoErrorEsperado = Left(ErrorServicio(Negocio, "la ruta supera las cuadras a la redonda a la cual esta permitido realizar domicilios que son 10, por favor ajuste las rutas en el archivo."))
        val posicion = Posicion(Coordenada(2, 4), N)

        val resultadoValidacion = waitForFutureResult(ServicioValidaciones.validarRuta(ruta, posicion).value.runToFuture)

        resultadoValidacion.isLeft mustBe true
        resultadoValidacion.leftSideValue mustBe resultadoErrorEsperado

      }
    }
  }
}
