package co.com.felipe.osorio.corrientazoS4N

import co.com.felipe.osorio.corrientazoS4N.servicios.ServicioCorrientazo
import monix.execution.Scheduler.Implicits.global

object Prueba extends {

  def main(args: Array[String]): Unit = {

    println("=======Prueba Andres Felipe Osorio, Su corrientazoS4N======")

    val path ="src/main/scala/co/com/felipe/osorio/corrientazoS4N/archivosEntrada"
    val listaArchivos = List("in01.txt", "in02.txt", "in03.txt", "in04.txt", "in05.txt", "in06.txt", "in07.txt", "in08.txt", "in09.txt", "in10.txt", "in11.txt", "in12.txt", "in13.txt", "in14.txt", "in15.txt", "in16.txt", "in17.txt", "in18.txt", "in19.txt", "in20.txt")
    listaArchivos.map(ServicioCorrientazo.enviarDomiciliosConDron(_, path).value.runAsync {
      resultado =>
        resultado match {
          case Left(exc) => println(s"Error  exception, la excepcion es: ${exc.getMessage}")
          case Right(value) => {
            value match {
              case Right(dron) => println(s"El Dron Final es: $dron")
              case Left(errorServicio) => println(s"Ocurrio un Error, el Error es:${errorServicio.mensaje}")
            }
          }
        }
    })
  }
}
