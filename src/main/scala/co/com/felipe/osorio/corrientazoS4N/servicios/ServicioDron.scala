package co.com.felipe.osorio.corrientazoS4N.servicios

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.dominio.{Coordenada, Entrega, ErrorServicio, Instruccion, N, Posicion, Ruta}
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult
import monix.eval.Task

import scala.annotation.tailrec
import scala.collection.IterableOnce.iterableOnceExtensionMethods

trait ServicioDron {

  @tailrec
  final def realizarRuta(ruta: Ruta, posicionInicial:Posicion, listaPosiciones:List[Posicion]= Nil ): EitherTResult[List[Posicion]] = {
    ruta.listaDomicilios match {
      case Nil =>  EitherT.rightT[Task, ErrorServicio](listaPosiciones)
      case _ =>{
        val posicion =ServicioDomicilio.entregar(ruta.listaDomicilios.head.listaInstrucciones, posicionInicial)
        realizarRuta(Ruta(ruta.listaDomicilios.tail),posicion, listaPosiciones ++ List(posicion))
      }
    }
  }

  def validarRuta(listaInstruccionesPedido : List[String]) = {
    generarListaInstrucciones(listaInstruccionesPedido)
  }

  private def generarListaInstrucciones(lista: List[String]) = {
    val listaInstrucciones =lista.map(_.map(Instruccion(_)))
    listaInstrucciones.map(x=>x.map(x=>x))
   // val entrega = Entrega(listaInstrucciones)
  }

  def obtenerDron()
}

object ServicioDron extends ServicioDron
