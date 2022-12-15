import controllers.ExamenController
import db.HibernateManager
import db.getBatallasInit
import db.getNavesInit
import db.getPilotosInit
import repositories.batalla.BatallaRepositoryImpl
import repositories.nave.NaveRepositoryImpl
import repositories.piloto.PilotoRepositoryImpl

/*
-- ENUNCIADO (segun recuerdo) --

R2D2 crea una BBDD usando JPA + Hibernate y en ella almacena tres entidades: naves, pilotos y batallas

- Naves: identificador, tipo de nave (X-WIND, T-FIGHTER), fecha de alta, misiles protonicos y salto hiper espacial.
- Piloto: Identificador, nombre, planeta de origen, fecha incorporacion, experiencia.
- Batalla: Identificador, planeta, fecha

Teniendo en cuenta lo anterior, se agregan los siguientes datos:

Una nave solo puede pertenecer a un piloto y un piloto solo puede tener una nave.
En una batalla pueden participar muchos pilotos, y ellos solo podian participar en 1, y como maximo uno de ellos puede ser capitan

Se pedia:
- Plantear las relaciones entre cada entidad, preparar los modelos con las notaciones respectivas.
- Realizar las operaciones CRUD de la BBDD (repositorios, controladores y main)
- Realizar los test de controladores y repositorios

Tiempo para resolverlo: 4h:30min
* */
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

    // Obtengo los datos introducidos
    controller.getNaves().forEach { println(it) }
    controller.getPilotos().forEach { println(it) }
    controller.getBatallas().forEach { println(it) }

    // FindById
    val updateBatalla = controller.getBatallaById(1)

    // Update
    updateBatalla?.let {
        controller.getPilotoById(1)
            ?.let { it1 -> it.pilotosInvolucrados.add(it1) } // Cambiar id por 4 para que salte mensaje
        controller.getPilotoById(2)?.let { it2 -> it.pilotosInvolucrados.add(it2) }
        controller.updateBatalla(it)
    }

    println(controller.getBatallas())

    // Delete - Batalla
    controller.getBatallaById(1)?.let { controller.deleteBatalla(it) }

    controller.getBatallas().forEach { println(it) }

    // Delete - Piloto
    controller.getPilotoById(1)?.let { controller.deletePiloto(it) }

    controller.getPilotos().forEach { println(it) }

    // Delete - Nave -- Es posible si no tiene ningun piloto asociado
    controller.getNaveById(1)?.let { controller.deleteNave(it) }

    controller.getNaves().forEach { println(it) }

}

fun initDataBase() {
    // Probamos la conexión a la base de datos e inicamos los datos!!
    HibernateManager.open()
    HibernateManager.close()
}
