package co.com.felipe.osorio.corrientazoS4N.transformadores

import co.com.felipe.osorio.corrientazoS4N.dominio.Dron

trait TransformadorInfoEscritura {

  def transformarInfoDronAFormatoArchivoEscritura(dron:Dron): (List[String], String) = {
    val lista = dron.listaPosicionesSalida.map(posicion => {
      s"( ${posicion.coordenada.posicionX.toString}, ${posicion.coordenada.posicionY.toString}) ${posicion.orientacion.toString}"
    })
    val path = "src/main/scala/co/com/felipe/osorio/corrientazoS4N/archivosSalida"
    (lista,path)
  }

}

object TransformadorInfoEscritura extends TransformadorInfoEscritura
