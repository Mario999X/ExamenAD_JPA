package models

import java.time.LocalDate
import javax.persistence.*

// Marco las clases como entity y las coloco en el persistance, para que asi JPA las reconozca
@Entity
// Mania mia el querer cambiar el nombre de la tabla, no seria necesario, el indicar @Table si.
@Table(name = "naves")
@NamedQuery(name = "Nave.findAll", query = "SELECT n FROM Nave n")
data class Nave(
    val tipoNave: TipoNave,
    // Con estas notaciones indicamos que es el identificador principal y que se genere al introducirse
    @Id @GeneratedValue
    val id: Long,
    val fechaAlta: LocalDate,
    val misilesProtonicos: Int,
    val saltoHiperEspacio: Boolean,
) {

    enum class TipoNave {
        X_WIND, T_FIGHTER
    }
    // Tengo en cuenta el problema de la recursividad, si indicara al piloto, el piloto mostraria la nave que tiene asignada.
    // Otra solucion seria mostrar solo el ID o el nombre del piloto.
    override fun toString(): String {
        return "Nave(tipoNave='$tipoNave', id=$id, fechaAlta=$fechaAlta, misilesProtonicos=$misilesProtonicos, saltoHiperEspacio=$saltoHiperEspacio)"
    }
}
