package co.com.felipe.osorio.corrientazoS4N.servicios

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.dominio._
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult
import monix.eval.Task

import scala.annotation.tailrec

trait ServicioDron {

  @tailrec
  final def simularRuta(ruta: Ruta, posicionInicial:Posicion, listaPosiciones:List[Posicion]= Nil ): EitherTResult[List[Posicion]] = {
    ruta.listaDomicilios match {
      case Nil =>  EitherT.rightT[Task, ErrorServicio](listaPosiciones)
      case _ =>{
        val posicion =ServicioEntrega.obtenerPosicionEntrega(ruta.listaDomicilios.head.listaInstrucciones, posicionInicial)
        simularRuta(Ruta(ruta.listaDomicilios.tail),posicion, listaPosiciones ++ List(posicion))
      }
    }
  }

  @tailrec
  final def realizarRuta(dron: Dron): EitherTResult[Dron] = {
    dron.ruta.listaDomicilios match {
      case Nil => EitherT.rightT[Task, ErrorServicio](dron)
      case _ => {
        val dronActual = relizarEntrega(dron)
        realizarRuta(dron.copy(ruta = Ruta(dron.ruta.listaDomicilios.tail), posicion= dronActual.posicion, listaPosicionesSalida = dron.listaPosicionesSalida ++ List(dronActual.posicion)))
      }
    }
  }

  @tailrec
  private final def relizarEntrega(dron: Dron):Dron = {
     dron.ruta.listaDomicilios.headOption.map(_.listaInstrucciones).getOrElse(Nil) match {
      case Nil => dron
      case lista => {
        val listaInstrucciones = dron.ruta.listaDomicilios.map(_.listaInstrucciones).headOption.map(_.tail).getOrElse(Nil)
        val posicionActual = ServicioPosicion.actualizarPosicion(dron.posicion, lista.head)
        relizarEntrega(dron.copy(  posicion =posicionActual, ruta = Ruta(List(Entrega(listaInstrucciones))), listaPosicionesSalida = dron.listaPosicionesSalida ++ List(posicionActual) ))
      }
    }

  }



}

object ServicioDron extends ServicioDron
