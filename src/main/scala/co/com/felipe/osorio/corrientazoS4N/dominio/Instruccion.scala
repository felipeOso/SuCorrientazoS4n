
package co.com.felipe.osorio.corrientazoS4N.dominio

sealed trait Instruccion

case object A extends Instruccion {
 // override def toString: String = "Avanzar"
}
case object D extends Instruccion {
  //override def toString: String = "Giro 90° a la derecha"
}
case object I extends Instruccion {
  //override def toString: String = "Giro 90° a la Izquierda"
}

object Instruccion {
  def apply(caracter: Char): Instruccion = {
    caracter match {
      case 'A' => A
      case 'D' => D
      case 'I' => I
      case _ => throw new Exception(s"Caracter invalido $caracter solo son validos los caracteres ${A.toString}, ${D.toString}, ${I.toString}")
    }
  }
}