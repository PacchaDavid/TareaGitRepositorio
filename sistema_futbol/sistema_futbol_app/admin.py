from django.contrib import admin
from .models import *

#Modelos de base.py
#======================================================================================================================
admin.site.register(Campeonato)
admin.site.register(Equipo)
admin.site.register(Patrocinador)
admin.site.register(TablaPosiciones)
admin.site.register(RowTablaPosiciones)
# clase Abstracta admin.site.register(Persona)

#Modelos de partido_models.py
#======================================================================================================================
admin.site.register(Partido)
admin.site.register(PartidoCampeonato)
admin.site.register(Marcador)
admin.site.register(Tarjeta)
admin.site.register(Gol)
admin.site.register(Cambio)
admin.site.register(Falta)
# clase Abstracta admin.site.register(EventoPartido)
# clase Abstracta admin.site.register(OtrosEventos)

#Modelos de persona_models.py
#======================================================================================================================
admin.site.register(Jugador)
admin.site.register(Arbitro)
admin.site.register(DirectorTecnico)
admin.site.register(JugadorDeEquipo)

#Modelos de statistical_models.py
#======================================================================================================================
admin.site.register(EstadisticaGlobalEquipo)
admin.site.register(EstadisticaCampeonatoEquipo)
admin.site.register(EstadisticaPartido)
admin.site.register(EstadisticaGlobalJugador)
admin.site.register(EstadisticaCampeonatoJugador)
