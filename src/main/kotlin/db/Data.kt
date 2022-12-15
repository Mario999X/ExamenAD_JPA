package db

import models.Batalla
import models.Nave
import models.Piloto
import java.time.LocalDate

fun getNavesInit() = listOf(
    Nave(
        id = 1,
        tipoNave = Nave.TipoNave.T_FIGHTER,
        fechaAlta = LocalDate.now(),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = false,
    ),
    Nave(
        id = 2,
        tipoNave = Nave.TipoNave.X_WIND,
        fechaAlta = LocalDate.now().minusDays(2),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = true,
    ),
    Nave(
        id = 3,
        tipoNave = Nave.TipoNave.T_FIGHTER,
        fechaAlta = LocalDate.now(),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = false,
    ),
    Nave(
        id = 4,
        tipoNave = Nave.TipoNave.X_WIND,
        fechaAlta = LocalDate.now().minusDays(50),
        misilesProtonicos = (10..20).random(),
        saltoHiperEspacio = true,
    )
)

fun getPilotosInit() = listOf(
    Piloto(
        id = 1,
        nombre = "Kratos",
        planetaOrigen = "Tierra",
        fechaIncorporacion = LocalDate.now().minusYears(2),
        navePilotada = getNavesInit()[0],
        capitan = false
    ),
    Piloto(
        id = 2,
        nombre = "Atreus",
        planetaOrigen = "Tierra",
        fechaIncorporacion = LocalDate.now().minusYears(1),
        navePilotada = getNavesInit()[1],
        capitan = true
    ),
    Piloto(
        id = 3,
        nombre = "Obi-Wan",
        planetaOrigen = "Desconocido",
        fechaIncorporacion = LocalDate.now().minusYears(5),
        navePilotada = getNavesInit()[2],
        capitan = false
    ),
    Piloto(
        id = 4,
        nombre = "Anakin",
        planetaOrigen = "Desconocido",
        fechaIncorporacion = LocalDate.now().minusYears(5),
        navePilotada = getNavesInit()[3],
        capitan = true
    )
)

fun getBatallasInit() = listOf(
    Batalla(
        id = 1,
        planeta = "Sturno",
        fecha = LocalDate.now().minusYears(2)
    )
)