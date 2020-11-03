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

        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosEntradaTest"
        val nombreArchivo = "in99.txt"
        val resultadoErrorEsperado = ErrorServicio(Aplicacion, "Error al leer archivo, revise que el archivo se encuentre en el path correspondiente y que existe e intente nuevamente.")

        val resultado = waitForFutureResult(ServicioArchivo.leerArchivo(nombreArchivo, path).value.runToFuture)

        resultado.isRight mustBe false
        resultado.left.get mustBe resultadoErrorEsperado

      }
    }
    "Al invocar la funcion leerArchivo" when {
      "- el nombre del archivo y el path existe, debe retornar una lista de instrucciones" in {

        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosEntradaTest"
        val nombreArchivo = "in01.txt"
        val resultadoEsperado =( "01", List("AAAID", "AAAI", "AADAI"))

        val resultado = waitForFutureResult(ServicioArchivo.leerArchivo(nombreArchivo, path).value.runToFuture)

        resultado.isRight mustBe true
        resultado.right.get mustBe resultadoEsperado

      }
    }

    "Al invocar la funcion escribirArchivo" when {
      "la escritura es correcta, debe generar un documento txt con el mensaje a escribir y retornar una ejecucion exitosa" in {

        val cuerpoMensaje = List(s"(-2, 4) ${N.toString}", s"(-3, 3) ${S.toString}")
        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosSalidaTest"

        val resultado = waitForFutureResult(ServicioArchivo.escribirArchivo(cuerpoMensaje, "1", path).value.runToFuture)

        resultado.isRight mustBe true
        resultado.right.get mustBe true

      }
    }

    "Al invocar la funcion escribirArchivo" when {
      "la escritura es erronea, debe retornar un error" in {

        val cuerpoMensaje = List(s"(-2, 4) ${N.toString}", s"(-3, 3) ${S.toString}")
        val path = "src/test/scala/co/com/felipe/osorio/corrientazoS4N/archivosXXXXX"
        val resultadoErrorEsperado = ErrorServicio(Aplicacion,"Error al escribir archivo verifique e intente nuevamente.")

        val resultado = waitForFutureResult(ServicioArchivo.escribirArchivo(cuerpoMensaje, "18", path).value.runToFuture)

        resultado.isLeft mustBe true
        resultado.left.get mustBe resultadoErrorEsperado

      }
    }

  }



}
