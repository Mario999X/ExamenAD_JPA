import controllers.ExamenController
import db.HibernateManager
import db.getBatallasInit
import db.getNavesInit
import db.getPilotosInit
import mu.KotlinLogging
import repositories.batalla.BatallaRepositoryImpl
import repositories.nave.NaveRepositoryImpl
import repositories.piloto.PilotoRepositoryImpl

fun main() {
    initDataBase()

    // Controlador
    val controller = ExamenController(PilotoRepositoryImpl(), NaveRepositoryImpl(), BatallaRepositoryImpl())

    // Insercion de datos
    val navesInit = getNavesInit()
    navesInit.forEach { nave ->
        controller.createNave(nave)
    }
    val pilotosInit = getPilotosInit()
    pilotosInit.forEach { piloto ->
        controller.createPiloto(piloto)
    }
    val batallas = getBatallasInit()
    batallas.forEach { batalla ->
        controller.createBatalla(batalla)
    }

    controller.getNaves().forEach { println(it) }
    controller.getPilotos().forEach { println(it) }
    controller.getBatallas().forEach { println(it) }

    val updateBatalla = controller.getBatallaById(1)

    updateBatalla?.let {
        controller.getPilotoById(1)?.let { it1 -> it.pilotosInvolucrados.add(it1) }
        controller.getPilotoById(2)?.let { it2 -> it.pilotosInvolucrados.add(it2) }
        controller.updateBatalla(it)
    }

    println(controller.getBatallas())

}

fun initDataBase() {
    // Probamos la conexión a la base de datos e inicamos los datos!!
    HibernateManager.open()
    HibernateManager.close()
}
