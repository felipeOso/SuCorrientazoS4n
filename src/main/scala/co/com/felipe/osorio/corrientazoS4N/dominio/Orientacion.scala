package co.com.felipe.osorio.corrientazoS4N.dominio

sealed trait Orientacion

case object N extends Orientacion{
  override def toString: String = "Norte"
}
case object S extends Orientacion{
  override def toString: String = "Sur"
}
case object E extends Orientacion{
  override def toString: String = "Este"
}
case object O extends Orientacion{
  override def toString: String = "Oeste"
}