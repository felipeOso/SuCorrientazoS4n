package co.com.felipe.osorio.corrientazoS4N.dominio

sealed trait TipoError

case object Aplicacion extends TipoError
case object Negocio extends TipoError
