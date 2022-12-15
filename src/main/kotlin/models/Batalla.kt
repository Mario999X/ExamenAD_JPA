package models

import java.time.LocalDate
import javax.persistence.*

// Marco las clases como entity y las coloco en el persistance, para que asi JPA las reconozca
@Entity
// Mania mia el querer cambiar el nombre de la tabla, no seria necesario, el indicar @Table si.
@Table(name = "batallas")
// Dejamos lista una consulta que usaremos en el repositorioImpl para ser usada de forma general.
@NamedQuery(name = "Batalla.findAll", query = "SELECT b FROM Batalla b")
data class Batalla(
    @Id // Con estas notaciones indicamos que es el identificador principal
    val id: Long,
    val planeta: String,
    val fecha: LocalDate,
    // Indico el 1 a muchos en respuesta a "batalla" del modelo piloto.
    @OneToMany(fetch = FetchType.EAGER)
    var pilotosInvolucrados: MutableList<Piloto> = mutableListOf()
) {
    override fun toString(): String {
        return "Batalla(id=$id, planeta='$planeta', fecha=$fecha, pilotosInvolucrados:\n$pilotosInvolucrados)"
    }
}
