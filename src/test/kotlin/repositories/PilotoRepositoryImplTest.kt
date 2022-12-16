package repositories

import db.HibernateManager
import models.Nave
import models.Piloto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import repositories.piloto.PilotoRepositoryImpl
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PilotoRepositoryImplTest {

    private val repository = PilotoRepositoryImpl()

    private val data2 = Nave(
        id = 5,
        tipoNave = Nave.TipoNave.X_WIND,
        fechaAlta = LocalDate.now().minusDays(50),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = true,
    )

    private val data = Piloto(
        id = 5,
        nombre = "Kratos",
        planetaOrigen = "Tierra",
        fechaIncorporacion = LocalDate.now().minusYears(2),
        navePilotada = data2,
        capitan = false
    )

    @BeforeEach
    fun setUp() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM pilotos")
            query.executeUpdate()
        }

        HibernateManager.transaction {
            HibernateManager.manager.merge(data2)
        }

        HibernateManager.transaction {
            HibernateManager.manager.merge(data)
        }
    }

    @Test
    fun findAll() {
        val res = repository.findAll()
        assertEquals(res[0].id, data.id)
    }

    @Test
    fun findById() {
        val res = repository.findById(data.id)
        assert(5L == res?.id)
    }

    @Test
    fun save() {
        val res = repository.save(data)
        assert(res.id == data.id)
    }

    @Test
    fun delete() {
        repository.delete(data)
        val res = repository.findById(data.id)
        assertNull(res)
    }
}