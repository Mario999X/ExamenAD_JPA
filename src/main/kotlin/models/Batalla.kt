package models

import java.time.LocalDate
import javax.persistence.*

// Marco las clases como entity y las coloco en el persistance, para que asi JPA las reconozca
@Entity
@Table(name = "batallas") // Mania mia el querer cambiar el nombre de la tabla, no seria necesario, el indicar @Table si.
// Dejamos lista una consulta que usaremos en el repositorioImpl para ser usada de forma general.
@NamedQuery(name = "Batalla.findAll", query = "SELECT b FROM Batalla b")
data class Batalla(
    @Id // Con estas notaciones indicamos que es el identificador principal y que se genere al introducirse
    val id: Long,
    //@Column // Indicar column es opcional, sobretodo se marcaria si nos interesa cambiar el nombre de la variable para la tabla.
    val planeta: String,
    val fecha: LocalDate,
    /*
    * Las batallas cuentan con un listado de los pilotos que participaron en ellas, y por el enunciado, se ve que es una
    * relacion 1 a muchos; los pilotos solo participan en una batalla.
    *
    * Indico con el manyToOne y aplico joinColumn, en este caso el campo no es nullable, ya que prefiero que devuelva
    * una lista vacia.
    * */
    @OneToMany(fetch = FetchType.EAGER)
    var pilotosInvolucrados: MutableList<Piloto> = mutableListOf()
) {
    override fun toString(): String {
        return "Batalla(id=$id, planeta='$planeta', fecha=$fecha, pilotosInvolucrados:\n$pilotosInvolucrados)"
    }
}
