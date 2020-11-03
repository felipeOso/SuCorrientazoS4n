package co.com.felipe.osorio.corrientazoS4N.dominio

final case class Dron(id:String, posicion: Posicion, ruta: Ruta, listaPosicionesSalida:List[Posicion])
