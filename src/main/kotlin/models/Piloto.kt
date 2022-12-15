package models

import java.time.LocalDate
import javax.persistence.*

// Marco las clases como entity y las coloco en el persistance, para que asi JPA las reconozca
@Entity
// Mania mia el querer cambiar el nombre de la tabla, no seria necesario, el indicar @Table si.
@Table(name = "pilotos")
// Dejamos lista una consulta que usaremos en el repositorioImpl para ser usada de forma general.
@NamedQuery(name = "Piloto.findAll", query = "SELECT p FROM Piloto p")
data class Piloto(
    // Con estas notaciones indicamos que es el identificador principal
    @Id
    val id: Long,
    val nombre: String,
    val planetaOrigen: String,
    val fechaIncorporacion: LocalDate,
    val experiencia: Int = LocalDate.now().year.minus(fechaIncorporacion.year),
    /*
    * Indico la relacion 1-1 de piloto con nave, ya que un piloto solo puede tener una nave asignada
    * Y una nave no puede ser asignada a otro piloto
    *
    * Agrego un joinColumn indicando el nombre de la columna, la columna referenciada, si es posible
    * el NULL
    * */
    @OneToOne
    @JoinColumn(name = "nave_pilotada", referencedColumnName = "id", nullable = false)
    val navePilotada: Nave,
    /*
    * Indico la relacion muchos a 1
    * Aplico joinColumn con los respetivos datos
    * No voy a mostrar la batalla en el toString para evitar problemas de recursividad.
    * */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    val batalla: Batalla? = null,

    // Existe una restriccion en las batallas para que solo pueda actuar un capitan, esta se aplicara en el controller
    val capitan: Boolean
) {
    override fun toString(): String {
        return "Piloto(id=$id, nombre='$nombre', experiencia=$experiencia, planetaOrigen='$planetaOrigen', fechaIncorporacion=$fechaIncorporacion, navePilotada=$navePilotada, capitan=$capitan)"
    }
}
