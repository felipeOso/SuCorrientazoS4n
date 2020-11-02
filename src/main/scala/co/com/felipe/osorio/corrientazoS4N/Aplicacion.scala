package co.com.felipe.osorio.corrientazoS4N

import co.com.felipe.osorio.corrientazoS4N.servicios.ServicioArchivo
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
   }
}
