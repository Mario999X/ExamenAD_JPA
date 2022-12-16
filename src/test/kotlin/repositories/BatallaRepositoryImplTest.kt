package repositories

import db.HibernateManager
import models.Batalla
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import repositories.batalla.BatallaRepositoryImpl
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BatallaRepositoryImplTest {

    private val repository = BatallaRepositoryImpl()

    private val data = Batalla(
        id = 5,
        planeta = "Sturno",
        fecha = LocalDate.now().minusYears(2)
    )

    @BeforeEach
    fun setUp() {
        HibernateManager.transaction {
            val query = HibernateManager.manager.createNativeQuery("DELETE FROM batallas")
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