package co.com.felipe.osorio.corrientazoS4N.util

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

trait Utils {

  def waitForFutureResult[T]( future: Future[T] ): T =
    Await.result( future, Duration.Inf )
}

object Utils extends Utils
