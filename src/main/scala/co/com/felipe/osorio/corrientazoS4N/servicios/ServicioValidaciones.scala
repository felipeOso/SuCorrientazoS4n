package co.com.felipe.osorio.corrientazoS4N.servicios

import cats.data.EitherT
import co.com.felipe.osorio.corrientazoS4N.constantes.Constantes.ReglasNegocio.{CuadrasMaximasALaRedonda, NumeroMaximoEntregas}
import co.com.felipe.osorio.corrientazoS4N.dominio.{ErrorServicio, Negocio, Posicion, Ruta}
import co.com.felipe.osorio.corrientazoS4N.types.Types.EitherTResult
import monix.eval.Task

trait ServicioValidaciones {

  def validarRuta(ruta:Ruta, posicion:Posicion):EitherTResult[Boolean] = {
    if (ruta.listaDomicilios.length > NumeroMaximoEntregas)
      EitherT.leftT[Task, Boolean](ErrorServicio(Negocio, s"El numero maximo de entregas son: ${NumeroMaximoEntregas}"))
    else {
      for {
        listaPosiciones <- ServicioDron.simularRuta(ruta, posicion)
        esValido <- validarPosiciones(listaPosiciones)
      } yield esValido
    }
  }

  private def validarPosiciones(listaPosiciones: List[Posicion]):EitherTResult[Boolean] = {
    val listaPosicionesFueraDeAlcance = listaPosiciones.filter(posicion=>(Math.abs(posicion.coordenada.posicionX)>CuadrasMaximasALaRedonda || Math.abs(posicion.coordenada.posicionY)>CuadrasMaximasALaRedonda))
    listaPosicionesFueraDeAlcance match {
      case Nil => EitherT.rightT[Task,ErrorServicio](true)
      case _   => EitherT.leftT[Task,Boolean](ErrorServicio(Negocio, s"la ruta supera las cuadras a la redonda a la cual esta permitido realizar domicilios que son ${CuadrasMaximasALaRedonda}, por favor ajuste las rutas en el archivo." ))
    }
  }


}

object ServicioValidaciones extends ServicioValidaciones