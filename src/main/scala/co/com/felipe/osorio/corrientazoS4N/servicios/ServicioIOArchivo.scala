package co.com.felipe.osorio.corrientazoS4N.servicios

import java.io.{BufferedWriter, File, FileWriter}

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.dominio.{Aplicacion, ErrorServicio}
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult
import monix.eval.Task

import scala.io.Source
import scala.util.{Failure, Success, Try}

sealed trait ServicioIOArchivo {

  def leerArchivo(nombreArchivo: String): EitherTResult[List[String]]

  def escribirArchivo(lista: List[String], idDron: Int, path: String): EitherTResult[Boolean]

}

sealed trait ServicioArchivo extends ServicioIOArchivo {

  def leerArchivo(nombreArchivo: String): EitherTResult[List[String]] = {
    EitherT(
      Task {
        Try(Source.fromFile(s"src/main/scala/co/com/felipe/osorio/corrientazoS4N/archivosEntrada/$nombreArchivo"))
      }.map { resultado =>
          resultado match {
            case Success(buffer) => Right(buffer.getLines.toList)
            case Failure(_) => Left(ErrorServicio(Aplicacion, "Error al leer archivo, intente nuevamente."))
          }
        })
  }

  def escribirArchivo(cuerpoMensaje: List[String], idDron: Int, path:String): EitherTResult[Boolean] = {
    EitherT(
      Task {
        Try {
          val bufferWriter = new BufferedWriter(new FileWriter(new File(path + s"/out${idDron}.txt")))
          bufferWriter.write("== Reporte de entregas ==\n ")
          bufferWriter.newLine()
          cuerpoMensaje.map(mensajeLinea => {
            bufferWriter.write(mensajeLinea +"\n" )

          })
          bufferWriter.close()
        }
      }.map(resultado => resultado match {
        case Success(_) => Right(true)
          case Failure(_) => Left(ErrorServicio(Aplicacion, "Error al escribir archivo, intente nuevamente."))
      }))
  }


}

object ServicioArchivo extends ServicioArchivo
