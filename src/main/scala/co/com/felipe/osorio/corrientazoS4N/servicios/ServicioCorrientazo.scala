package co.com.felipe.osorio.corrientazoS4N.servicios

import co.com.felipe.osorio.corrientazoS4N.dominio._
import co.com.felipe.osorio.corrientazoS4N.transformadores.TransformadorInfoEscritura
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult

trait ServicioCorrientazo {

  def enviarDomiciliosConDron(nombreArchivo:String, path:String):EitherTResult[Dron] ={
    for{
      tuplaIdDronListaDomicilio <- ServicioArchivo.leerArchivo(nombreArchivo, path)
      listaInstrucciones =tuplaIdDronListaDomicilio._2.map(_.map(Instruccion(_)))
      listaEntregas =listaInstrucciones.map(x=>Entrega(x.toList))
      _ <- ServicioValidaciones.validarRuta(Ruta(listaEntregas),Posicion(Coordenada(0,0),N))
      dron= Dron( id = tuplaIdDronListaDomicilio._1,Posicion(Coordenada(0,0),N),Ruta(listaEntregas),Nil)
      dronFinal <- ServicioDron.realizarRuta(dron)
      tuplaCuerpoMensajeYPath= TransformadorInfoEscritura.transformarInfoDronAFormatoArchivoEscritura(dronFinal)
      _ <- ServicioArchivo.escribirArchivo(tuplaCuerpoMensajeYPath._1,dronFinal.id,tuplaCuerpoMensajeYPath._2)
    } yield  dronFinal
  }
}

object ServicioCorrientazo extends ServicioCorrientazo
