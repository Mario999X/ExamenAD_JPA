package repositories.batalla

import models.Batalla
import repositories.CrudRepository

// Interfaz que implementa el CrudRepositoru, es perfecto por si queremos agregar metodos a este repositorio en especial
interface BatallaRepository : CrudRepository<Batalla, Long> {
}