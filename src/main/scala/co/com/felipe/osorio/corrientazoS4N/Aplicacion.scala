package co.com.felipe.osorio.corrientazoS4N

import co.com.felipe.osorio.corrientazoS4N.dominio.{A, Coordenada, D, Entrega, I, Instruccion, N, Posicion, Ruta}
import co.com.felipe.osorio.corrientazoS4N.servicios.{ServicioArchivo, ServicioDron}
import monix.execution.Scheduler.Implicits.global

object Prueba extends {

   def main(args: Array[String]): Unit = {

   ServicioArchivo.leerArchivo("in01.txt").value.runAsync {
       resultado =>
         resultado match {
           case Left(_) => println("Error")
           case Right(value) => {
             value match {
               case Right(value) => println(s"el archivo es $value")
               case Left(value) => println("Error 2")
             }
           }
         }
     }

     val listaEntrega = List(Entrega(List(A,A,A,A,I,A, A)))
     val posicionInicial = Posicion(Coordenada(0,0), N)
      println("va entrar")
   val hh = for{
       listaRutaString <- ServicioArchivo.leerArchivo("in01.txt")
       //validar ruta acaconvertimos de lista string a instruccion y entregamos una lista de entrega
       xx <- ServicioDron.realizarRuta(Ruta(listaEntrega),posicionInicial)
     cc <- ServicioArchivo.escribirArchivo()
     }yield {
      println("el archivo que se leyo es" + listaRutaString)
       println("la lista de posiciones es " + xx)
     }

     val listaa = List("AIAD", "AAAAIAD")
     if(listaa.length > 3) println("mandar Error servicio con eihertLEFT") else println("bien")
     val listaInstrucciones =listaa.map(_.map(Instruccion(_)))
     val listaEntregas =listaInstrucciones.map(x=>Entrega(x.toList))
     println("vamos a ver la lista"+ listaInstrucciones)
     println("vamos a ver la listaEntrega"+ listaEntregas)
  //simular
     hh.value.runAsync {
       resultado =>
         resultado match {
           case Left(_) => println("Error  execption")
           case Right(value) => {
             value match {
               case Right(value) => println(s"la pos es $value")
               case Left(value) => println("Error  execption 2")
             }
           }
         }
     }

   }
}
