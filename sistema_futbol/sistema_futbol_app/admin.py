from django.contrib import admin
from .models import *

admin.site.register(Campeonato)
admin.site.register(Equipo)
admin.site.register(Jugador)
admin.site.register(Arbitro)
admin.site.register(Patrocinador)
admin.site.register(RowTablaPosiciones)
admin.site.register(TablaPosiciones)
admin.site.register(Partido)
admin.site.register(Marcador)
admin.site.register(EstadisticaEquipo)
admin.site.register(EstadisticaJugador)
admin.site.register(EstadisticaPartido)
admin.site.register(Cambio)
admin.site.register(Gol)
admin.site.register(Falta)
admin.site.register(Tarjeta)

