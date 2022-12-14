package controllers

import models.Batalla
import models.Nave
import models.Piloto
import mu.KotlinLogging
import repositories.batalla.BatallaRepository
import repositories.nave.NaveRepository
import repositories.piloto.PilotoRepository

private val log = KotlinLogging.logger {}

class ExamenController(
    private val pilotoRepository: PilotoRepository,
    private val naveRepository: NaveRepository,
    private val batallaRepository: BatallaRepository
) {
    // Aqui realizariamos las operaciones, y ademas, al pasarle los repositorios podemos tener solo un controlador.
    // Como se pide en el examen.

    // PILOTOS
    fun createPiloto(piloto: Piloto): Piloto {
        log.debug { "Creando $piloto" }
        pilotoRepository.save(piloto)
        return piloto
    }

    fun getPilotos(): List<Piloto> {
        log.debug { "Obteniendo pilotos" }
        return pilotoRepository.findAll()
    }

    fun getPilotoById(id: Long): Piloto? {
        log.debug { "Obteniendo piloto con id $id" }
        return pilotoRepository.findById(id)
    }

    fun updatePiloto(piloto: Piloto): Piloto {
        log.debug { "Actualizando $piloto" }
        return pilotoRepository.save(piloto)
    }

    fun deletePiloto(it: Piloto): Boolean {
        log.debug { "Borrando $it" }
        return pilotoRepository.delete(it)
    }

    // NAVES
    fun createNave(nave: Nave): Nave {
        log.debug { "Creando $nave" }
        naveRepository.save(nave)
        return nave
    }

    fun getNaves(): List<Nave> {
        log.debug { "Obteniendo naves" }
        return naveRepository.findAll()
    }

    fun getNaveById(id: Long): Nave? {
        log.debug { "Obteniendo nave con id $id" }
        return naveRepository.findById(id)
    }

    fun updateNave(nave: Nave): Nave {
        log.debug { "Actualizando $nave" }
        return naveRepository.save(nave)
    }

    fun deleteNave(it: Nave): Boolean {
        log.debug { "Borrando $it" }
        return naveRepository.delete(it)
    }

    // BATALLAS
    fun createBatalla(batalla: Batalla): Batalla {
        log.debug { "Creando $batalla" }
        batallaRepository.save(batalla)
        return batalla
    }

    fun getBatallas(): List<Batalla> {
        log.debug { "Obteniendo batallas" }
        return batallaRepository.findAll()
    }

    fun getBatallaById(id: Long): Batalla? {
        log.debug { "Obteniendo batalla con id $id" }
        return batallaRepository.findById(id)
    }

    fun updateBatalla(batalla: Batalla): Batalla {
        log.debug { "Actualizando $batalla" }
        return batallaRepository.save(batalla)
    }

    fun deleteBatalla(it: Batalla): Boolean {
        log.debug { "Borrando $it" }
        return batallaRepository.delete(it)
    }

}