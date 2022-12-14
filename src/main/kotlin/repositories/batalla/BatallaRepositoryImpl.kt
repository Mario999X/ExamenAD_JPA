package repositories.batalla

import db.HibernateManager
import db.HibernateManager.manager
import models.Batalla
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val log = KotlinLogging.logger { }

class BatallaRepositoryImpl : BatallaRepository {
    override fun findAll(): List<Batalla> {
        log.debug { "findAll()" }
        var batallas = mutableListOf<Batalla>()
        HibernateManager.query {
            val query: TypedQuery<Batalla> = manager.createNamedQuery("Batalla.findAll", Batalla::class.java)
            batallas = query.resultList
        }
        return batallas
    }

    override fun findById(id: Long): Batalla? {
        log.debug { "findById($id)" }
        var batalla: Batalla? = null
        HibernateManager.query {
            batalla = manager.find(Batalla::class.java, id)
        }
        return batalla
    }

    override fun save(entity: Batalla): Batalla {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Batalla): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val batalla = manager.find(Batalla::class.java, entity.id)
            batalla?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}