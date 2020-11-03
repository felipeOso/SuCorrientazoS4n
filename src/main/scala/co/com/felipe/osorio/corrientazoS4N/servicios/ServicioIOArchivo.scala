package co.com.felipe.osorio.corrientazoS4N.servicios

import java.io.{BufferedWriter, File, FileWriter}

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.dominio.{Aplicacion, ErrorServicio}
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult
import monix.eval.Task

import scala.io.Source
import scala.util.{Failure, Success, Try}

sealed trait ServicioIOArchivo {

  def leerArchivo(nombreArchivo: String, path:String): EitherTResult[(String,List[String])]

  def escribirArchivo(lista: List[String], idDron: String, path: String): EitherTResult[Boolean]

}

sealed trait ServicioArchivo extends ServicioIOArchivo {

  def leerArchivo(nombreArchivo: String, path:String): EitherTResult[(String, List[String])] = {
    EitherT(
      Task {
        Try(Source.fromFile(s"$path/$nombreArchivo"))
      }.map {
        _ match {
          case Success(buffer) => {
            val listaDomicilio = buffer.getLines.toList
            val idDron = nombreArchivo.substring(2, nombreArchivo.lastIndexOf(".txt"))
            Right(idDron,listaDomicilio)
          }
          case Failure(_) => Left(ErrorServicio(Aplicacion, "Error al leer archivo, revise que el archivo se encuentre en el path correspondiente y que existe e intente nuevamente."))
        }
      })
  }

  def escribirArchivo(cuerpoMensaje: List[String], idDron: String, path: String): EitherTResult[Boolean] = {
    EitherT(
      Task {
        Try {
          val bufferWriter = new BufferedWriter(new FileWriter(new File(path + s"/out${idDron}.txt")))
          bufferWriter.write("== Reporte de entregas ==\n ")
          bufferWriter.newLine()
          cuerpoMensaje.map(mensajeLinea => {
            bufferWriter.write(mensajeLinea + "\n")

          })
          bufferWriter.close()
        }
      }.map(_ match {
        case Success(_) => Right(true)
        case Failure(_) => Left(ErrorServicio(Aplicacion, "Error al escribir archivo verifique e intente nuevamente."))
      }))
  }

}

object ServicioArchivo extends ServicioArchivo
