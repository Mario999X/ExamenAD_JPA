package repositories

import db.HibernateManager
import models.Nave
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import repositories.nave.NaveRepositoryImpl
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class NaveRepositoryImplTest {

    private val repository = NaveRepositoryImpl()

    private val data = Nave(
        id = 5,
        tipoNave = Nave.TipoNave.X_WIND,
        fechaAlta = LocalDate.now().minusDays(50),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = true,
    )

    @BeforeEach
    fun setUp() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM naves")
            query.executeUpdate()
        }
        HibernateManager.transaction {
            HibernateManager.manager.merge(data)
        }
    }

    @Test
    fun findAll() {
        val res = repository.findAll()
        Assertions.assertEquals(res[0].id, data.id)
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
        Assertions.assertNull(res)
    }
}