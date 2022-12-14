package repositories.piloto

import models.Piloto
import repositories.CrudRepository

// Interfaz que implementa el CrudRepositoru, es perfecto por si queremos agregar metodos a este repositorio en especial
interface PilotoRepository : CrudRepository<Piloto, Long> {
}