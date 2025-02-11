package com.example.intf_appempleados.data

import com.example.intf_appempleados.R

data class ContactInfo(
    val telefono: String,
    val nombreContacto: String? = null,
    val telefonoContacto: String? = null,
    val emailContacto: String? = null
)

// Lista de ciudades disponibles
val ciudadesContacto = listOf(
    "Madrid, España",
    "París, Francia",
    "Londres, Reino Unido",
    "Porto Alegre, Brasil",
    "Acapulco, México",
    "Vancouver, Canadá",
    "Houston, Estados Unidos",
    "Casablanca, Marruecos",
    "Osaka, Japón",
    "Melbourne, Australia",
    "Ankara, Turquía",
    "Dubai, Emiratos Árabes Unidos"
)

// Mapa de imágenes de cada país (debes agregar los recursos en res/drawable)
val imagenesPaises = mapOf(
    "Madrid, España" to R.drawable.mapa_espana,
    "París, Francia" to R.drawable.mapa_francia,
    "Londres, Reino Unido" to R.drawable.mapa_reino_unido,
    "Porto Alegre, Brasil" to R.drawable.mapa_brasil,
    "Acapulco, México" to R.drawable.mapa_mexico,
    "Vancouver, Canadá" to R.drawable.mapa_canada,
    "Houston, Estados Unidos" to R.drawable.mapa_eeuu,
    "Casablanca, Marruecos" to R.drawable.mapa_marruecos,
    "Osaka, Japón" to R.drawable.mapa_japon,
    "Melbourne, Australia" to R.drawable.mapa_australia,
    "Ankara, Turquía" to R.drawable.mapa_turquia,
    "Dubai, Emiratos Árabes Unidos" to R.drawable.mapa_emiratos
)

// Lista de servicios disponibles
val servicios = listOf("Emergencias", "Policía", "Bomberos", "Oficina de Información y Turismo", "Ayuntamiento", "Servicio de Taxi", "Oficina")

// Mapa de contactos por ciudad y servicio
val contactos = mapOf(
    "Madrid, España" to mapOf(
        "Emergencias" to ContactInfo("112"),
        "Policía" to ContactInfo("091"),
        "Bomberos" to ContactInfo("080"),
        "Oficina de Información y Turismo" to ContactInfo("+34 914 201 314"),
        "Ayuntamiento" to ContactInfo("+34 915 883 300"),
        "Servicio de Taxi" to ContactInfo("+34 915 474 700"),
        "Oficina" to ContactInfo("+34 913 423 600", "Antonio Avellaneda", "+34 913 423 634", "aavellaneda@splatnot.com")
    ),
    "París, Francia" to mapOf(
        "Emergencias" to ContactInfo("112"),
        "Policía" to ContactInfo("17"),
        "Bomberos" to ContactInfo("18"),
        "Oficina de Información y Turismo" to ContactInfo("+33 1 49 52 42 63"),
        "Ayuntamiento" to ContactInfo("+33 1 42 76 60 00"),
        "Servicio de Taxi" to ContactInfo("+33 1 45 30 30 30"),
        "Oficina" to ContactInfo("+33 1 45 26 20 30", "François Merlin", "+33 1 45 26 22 46", "fmerlin@splatnot.com")
    ),
    "Londres, Reino Unido" to mapOf(
        "Emergencias" to ContactInfo("999"),
        "Policía" to ContactInfo("101"),
        "Bomberos" to ContactInfo("999"),
        "Oficina de Información y Turismo" to ContactInfo("+44 20 7344 1000"),
        "Ayuntamiento" to ContactInfo("+44 20 7983 4000"),
        "Servicio de Taxi" to ContactInfo("+44 20 7272 0272"),
        "Oficina" to ContactInfo("+44 20 2536 0200", "Sarah Louise Taylor", "+44 20 2536 0232", "staylor@splatnot.com")
    ),
    "Porto Alegre, Brasil" to mapOf(
        "Emergencias" to ContactInfo("190"),
        "Policía" to ContactInfo("190"),
        "Bomberos" to ContactInfo("193"),
        "Oficina de Información y Turismo" to ContactInfo("+55 51 3289 4285"),
        "Ayuntamiento" to ContactInfo("+55 51 3289 1027"),
        "Servicio de Taxi" to ContactInfo("+55 51 3211 1188"),
        "Oficina" to ContactInfo("+55 51 5644 1000", "Maria Fernanda Oliveira Costa", "+55 51 5644 1688", "mfoliveira@splatnot.com")
    ),
    "Acapulco, México" to mapOf(
        "Emergencias" to ContactInfo("911"),
        "Policía" to ContactInfo("911"),
        "Bomberos" to ContactInfo("911"),
        "Oficina de Información y Turismo" to ContactInfo("+52 744 482 2855"),
        "Ayuntamiento" to ContactInfo("+52 744 482 1400"),
        "Servicio de Taxi" to ContactInfo("+52 744 485 1073"),
        "Oficina" to ContactInfo("+52 744 779 1000", "Antonio Avellaneda", "+52 744 779 1948", "aavellaneda@splatnot.com")
    ),
    "Vancouver, Canadá" to mapOf(
        "Emergencias" to ContactInfo("911"),
        "Policía" to ContactInfo("911"),
        "Bomberos" to ContactInfo("911"),
        "Oficina de Información y Turismo" to ContactInfo("+1 604 482 2888"),
        "Ayuntamiento" to ContactInfo("+1 604 873 7000"),
        "Servicio de Taxi" to ContactInfo("+1 604 681 1111"),
        "Oficina" to ContactInfo("+34 913 423 600", "David Miller", "+34 913 423 634", "dmiller@splatnot.com")
    ),
    "Houston, Estados Unidos" to mapOf(
        "Emergencias" to ContactInfo("911"),
        "Policía" to ContactInfo("713 884 3131"),
        "Bomberos" to ContactInfo("911"),
        "Oficina de Información y Turismo" to ContactInfo("+1 713 437 5240"),
        "Ayuntamiento" to ContactInfo("+1 713 247 1000"),
        "Servicio de Taxi" to ContactInfo("+1 713 236 1111"),
        "Oficina" to ContactInfo("+1 713 555 1222", "Robinson Hill", "+1 713 555 1291", "rhill@splatnot.com")
    ),
    "Casablanca, Marruecos" to mapOf(
        "Emergencias" to ContactInfo("19"),
        "Policía" to ContactInfo("19"),
        "Bomberos" to ContactInfo("15"),
        "Oficina de Información y Turismo" to ContactInfo("+212 522 225 410"),
        "Ayuntamiento" to ContactInfo("+212 522 235 157"),
        "Servicio de Taxi" to ContactInfo("+212 522 252 014"),
        "Oficina" to ContactInfo("+212 522 449 000", "Ahmed Ben Youssef El Fassi", "+212 522 449 644", "abenyoussef@splatnot.com")
    ),
    "Osaka, Japón" to mapOf(
        "Emergencias" to ContactInfo("110"),
        "Policía" to ContactInfo("110"),
        "Bomberos" to ContactInfo("119"),
        "Oficina de Información y Turismo" to ContactInfo("+81 6 6345 3301"),
        "Ayuntamiento" to ContactInfo("+81 6 6208 7171"),
        "Servicio de Taxi" to ContactInfo("+81 6 6345 1234"),
        "Oficina" to ContactInfo("+81 6 4882 6600", "Takahashi Hiroshi", "+81 6 4882 6632", "thiroshi@splatnot.com")
    ),
    "Melbourne, Australia" to mapOf(
        "Emergencias" to ContactInfo("000"),
        "Policía" to ContactInfo("000"),
        "Bomberos" to ContactInfo("000"),
        "Oficina de Información y Turismo" to ContactInfo("+61 3 9658 9658"),
        "Ayuntamiento" to ContactInfo("+61 3 9658 9658"),
        "Servicio de Taxi" to ContactInfo("+61 3 8413 7300"),
        "Oficina" to ContactInfo("+61 3 9974 9600", "Emily Johnson", "+61 3 9974 9677", "ejohnson@splatnot.com")
    ),
    "Ankara, Turquía" to mapOf(
        "Emergencias" to ContactInfo("112"),
        "Policía" to ContactInfo("155"),
        "Bomberos" to ContactInfo("110"),
        "Oficina de Información y Turismo" to ContactInfo("+90 312 310 13 55"),
        "Ayuntamiento" to ContactInfo("+90 312 507 10 00"),
        "Servicio de Taxi" to ContactInfo("+90 312 444 75 47"),
        "Oficina" to ContactInfo("+90 312 822 70 00", "Elif Demir", "+90 312 822 70 94", "edemir@splatnot.com")
    ),
    "Dubai, Emiratos Árabes Unidos" to mapOf(
        "Emergencias" to ContactInfo("999"),
        "Policía" to ContactInfo("999"),
        "Bomberos" to ContactInfo("997"),
        "Oficina de Información y Turismo" to ContactInfo("+971 4 201 5555"),
        "Ayuntamiento" to ContactInfo("+971 4 406 5555"),
        "Servicio de Taxi" to ContactInfo("+971 4 208 0808"),
        "Oficina" to ContactInfo("+971 4 495 7000", "Khalid Al Maktoum", "+971 4 495 7556", "kalmaktoum@splatnot.com")
    )
)
