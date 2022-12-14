package models

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

// Marco las clases como entity y las coloco en el persistance, para que asi JPA las reconozca
@Entity
// Mania mia el querer cambiar el nombre de la tabla, no seria necesario, el indicar @Table si.
@Table(name = "pilotos")
// Dejamos lista una consulta que usaremos en el repositorioImpl para ser usada de forma general.
@NamedQuery(name = "Piloto.findAll", query = "SELECT p FROM Piloto p")
data class Piloto(
    // Con estas notaciones indicamos que es el identificador principal y que se genere al introducirse
    @Id
    val id: Long,
    val nombre: String,
    val experiencia: Int,
    val planetaOrigen: String,
    val fechaIncorporacion: LocalDate,
    /*
    * Indico la relacion 1-1 de piloto con nave, ya que un piloto solo puede tener una nave asignada
    * Y una nave no puede ser asignada a otro piloto, por lo tanto, 1-1; por el enunciado entiendo que
    * cada piloto tiene una nave si o si.
    *
    * Agrego un joinColumn indicando el nombre de la columna, la columna referenciada, si es posible
    * el NULL, y la tabla referenciada.
    * */
    @OneToOne
    @JoinColumn(name = "nave_pilotada", referencedColumnName = "id", nullable = false)
    val navePilotada: Nave,
    /*
    * Indico la relacion 1 a muchos, e indico que sea nullable, un piloto solo puede participar en una de ellas.
    * Aplico joinColumn con los respetivos datos
    * No voy a mostrar la batalla en el toString para evitar problemas.
    * */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    val batalla: Batalla? = null,

    // Existe una restriccion en las batallas para que solo pueda actuar un capitan, esto podria resolverse con:
    // Boolean o con un ENUM, yo he decidio por un boolean por una razon (explicado en Nave, perdona.)
    val capitan: Boolean
) {
    // Genero el toString, teniendo siempre en cuenta que no exista recursividad y se lie.
    override fun toString(): String {
        return "Piloto(id=$id, nombre='$nombre', experiencia=$experiencia, planetaOrigen='$planetaOrigen', fechaIncorporacion=$fechaIncorporacion, navePilotada=$navePilotada, capitan=$capitan)"
    }
}
