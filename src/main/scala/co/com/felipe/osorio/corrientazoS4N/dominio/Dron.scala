package co.com.felipe.osorio.corrientazoS4N.dominio

final case class Dron1(id:Int, posicion: Posicion, numeroDomicilios:Int)
final case class Dron(id:Int, posicion: Posicion, ruta: Ruta, listaPosicionesSalida:List[Posicion])
