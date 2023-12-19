package com.development.petcare.objects.providers

import com.development.petcare.objects.basics.Hotel

class HotelProvider {
    companion object{

        var HotelList = mutableListOf<Hotel>(
            Hotel(1, "Cabaña del Abuelo", "Chiquimula", "4.5", "Hotel para todo tipo de mascotas con areas recreativas, area común, piscina con cuidadores y habitaciones individuales para cada mascota.","50$ la noche a 210$"),
            Hotel(2, "Rancho las Lomas", "Zacapa", "3.0", "Un rancho para todo tipo de mascota","30$ a 8$"),
            Hotel(3, "La Casa de Chichicho", "Ciudad de Guatemala", "4.0", "La casa del perro de paulina", "20$ a 40$" ),
            Hotel(4, "Vets", "Chiquimula", "2.0", "Hotel y veterinaria", "$20"),
            Hotel(5, "PetHotel", "Ciudad de Guatemala", "4.0","Pet Hotel Guatemala es un hotel para perros, gatos, aves, roedores y tortugas , cerca de la capital y ubicado en uno de los entornos naturales en San Lucas Sacatepéquez Guatemala, somos un eco-hotel creado y construido para el bienestar integral de su mascota. Además de ser un hotel somos un refugio privado de perros y gatos rescatados de situaciones criticas en la calle. con cada vez que alguien hospeda con nosotros nos ayuda a mantener el refugio Contamos con las mejores instalaciones para hospedar a su mejor amigo y la mejor atención.  Nuestra principal característica es el amor hacia los animales.", "120$ a 160$")
        )
    }
}