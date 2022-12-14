package repositories.piloto

import db.HibernateManager
import db.HibernateManager.manager
import models.Piloto
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val log = KotlinLogging.logger { }

class PilotoRepositoryImpl : PilotoRepository {
    override fun findAll(): List<Piloto> {
        log.debug { "findAll()" }
        var pilotos = mutableListOf<Piloto>()
        HibernateManager.query {
            val query: TypedQuery<Piloto> = manager.createNamedQuery("Piloto.findAll", Piloto::class.java)
            pilotos = query.resultList
        }
        return pilotos
    }

    override fun findById(id: Long): Piloto? {
        log.debug { "findById($id)" }
        var piloto: Piloto? = null
        HibernateManager.query {
            piloto = manager.find(Piloto::class.java, id)
        }
        return piloto
    }

    override fun save(entity: Piloto): Piloto {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Piloto): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val piloto = manager.find(Piloto::class.java, entity.id)
            piloto?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}