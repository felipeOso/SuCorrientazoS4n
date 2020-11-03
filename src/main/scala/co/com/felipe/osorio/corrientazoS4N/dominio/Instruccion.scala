
package co.com.felipe.osorio.corrientazoS4N.dominio

sealed trait Instruccion

case object A extends Instruccion
case object D extends Instruccion
case object I extends Instruccion

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