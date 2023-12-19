package com.development.petcare.objects.providers

import com.development.petcare.objects.basics.Walker

class WalkerProvider {
    companion object {
        var WalkerList = mutableListOf<Walker>(
            Walker(
                1,
                "Saul Monroy",
                "Chiquimula",
                "19",
                "Animales Doméscticos",
                "4.5",
                "Nothing",
                "Chico alto, atento, cariñoso con las mascotas, fuerte y muy paciente.",
                "Paseo a las mascotas las horas acordadas, les doy agua, comida, premios y descansos en los trayectos.",
                "12",
                "Tengo un curso de primeros auxilios para masctoas domésticas, tengo un curso intensivo en adiestramiento y control de mascotas.",
                "Lunes a sábado 6:00am a 6:00pm"
            ),
            Walker(
                2,
                "Any Marroquín",
                "Chiquimula",
                "45",
                "Perros",
                "4.0",
                "Nothing",
                "Mujer Bajita, atenta, cariñoso con las mascotas.",
                "Paseo a las mascotas las horas acordadas, les doy agua, comida, premios y descansos en los trayectos.",
                "12",
                "Tengo un curso de primeros auxilios para masctoas domésticas, tengo un curso intensivo en adiestramiento y control de mascotas.",
                "Lunes a sábado 8:00am a 8:00pm"
            )
        )

    }
}