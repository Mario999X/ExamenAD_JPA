package repositories.nave

import models.Nave
import repositories.CrudRepository

// Interfaz que implementa el CrudRepositoru, es perfecto por si queremos agregar metodos a este repositorio en especial
interface NaveRepository : CrudRepository<Nave, Long> {
}