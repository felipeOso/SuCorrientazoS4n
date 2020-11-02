package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio.{Aplicacion, ErrorServicio, N, S}
import co.com.felipe.osorio.corrientazoS4N.util.Utils.waitForFutureResult
import monix.execution.Scheduler.Implicits.global
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec

class ServicioIOArchivoTest extends AnyWordSpec {

  "Test ServicioArchivo" should {

    "Al invocar la funcion leerArchivo" when {
      "- el nombre del archivo no existe, debe retornar un Error indicando el tipo y descripcion del error" in {

        val nombreArchivo = "in99.txt"
        val resultadoErrorEsperado = ErrorServicio(Aplicacion, "Error al leer archivo, intente nuevamente.")

        val resultado = waitForFutureResult(ServicioArchivo.leerArchivo(nombreArchivo).value.runToFuture)

        resultado.isRight mustBe false
        resultado.left.get mustBe resultadoErrorEsperado

      }
    }
    "Al invocar la funcion leerArchivo" when {
      "- el nombre del archivo existe, debe retornar una lista de instrucciones" in {

        val nombreArchivo = "in01.txt"
        val resultadoEsperado = List("AAAIDDA", "JHJDFKSDJF")

        val resultado = waitForFutureResult(ServicioArchivo.leerArchivo(nombreArchivo).value.runToFuture)

        resultado.isRight mustBe true
        resultado.right.get mustBe resultadoEsperado

      }
    }

    "Al invocar la funcion escribirArchivo" when {
      "la escritura es correcta, debe generar un documento txt con el mensaje a escribir y retornar una ejecucion exitosa" in {

        val cuerpoMensaje = List(s"(-2, 4) ${N.toString}", s"(-3, 3) ${S.toString}")
        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosSalidaTest"

        val resultado = waitForFutureResult(ServicioArchivo.escribirArchivo(cuerpoMensaje, 1, path).value.runToFuture)

        resultado.isRight mustBe true
        resultado.right.get mustBe true

      }
    }

    "Al invocar la funcion escribirArchivo" when {
      "la escritura es erronea, debe retornar un error" in {

        val cuerpoMensaje = List(s"(-2, 4) ${N.toString}", s"(-3, 3) ${S.toString}")
        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosXXXXX"
        val resultadoErrorEsperado = ErrorServicio(Aplicacion,"Error al escribir archivo, intente nuevamente.")

        val resultado = waitForFutureResult(ServicioArchivo.escribirArchivo(cuerpoMensaje, 18, path).value.runToFuture)

        resultado.isLeft mustBe true
        resultado.left.get mustBe resultadoErrorEsperado

      }
    }

  }



}
