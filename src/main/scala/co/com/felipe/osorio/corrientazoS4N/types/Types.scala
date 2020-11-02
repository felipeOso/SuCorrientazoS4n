package co.com.felipe.osorio.corrientazoS4N.types

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.dominio.ErrorServicio
import monix.eval.Task

object Types {

  type EitherTResult[A] = EitherT[Task, ErrorServicio, A]

}
