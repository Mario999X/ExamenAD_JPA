package repositories.nave

import db.HibernateManager
import db.HibernateManager.manager
import models.Nave
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val log = KotlinLogging.logger { }

class NaveRepositoryImpl : NaveRepository {
    override fun findAll(): List<Nave> {
        log.debug { "findAll()" }
        var naves = mutableListOf<Nave>()
        HibernateManager.query {
            val query: TypedQuery<Nave> = manager.createNamedQuery("Nave.findAll", Nave::class.java)
            naves = query.resultList
        }
        return naves
    }

    // Implementamos un boolean que devuelva si existe o no y de esa forma y aplicando el find del manager
    // O devolvemos la nave o un null
    override fun findById(id: Long): Nave? {
        log.debug { "findById($id)" }
        var nave: Nave? = null
        HibernateManager.query {
            nave = manager.find(Nave::class.java, id)
        }
        return nave
    }

    // Creamos dos metodos que nos permitan o agregar la nave o actualizarla
    // Aplicando una logica parecida a la funcion superior.
    override fun save(entity: Nave): Nave {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    // Comprobamos que existe y aplicamos el manager.remove(entity)
    override fun delete(entity: Nave): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val nave = manager.find(Nave::class.java, entity.id)
            nave?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}