from django.db import models
from .base import *
from .partido_models import Partido
from .persona_models import *

class EstadisticaGlobalEquipo(models.Model):
    equipo = models.ForeignKey('Equipo',on_delete=models.CASCADE)

    def __str__(self):
        return f'Estadística global de {self.equipo.nombre}'

class EstadisticaCampeonatoEquipo(EstadisticaGlobalEquipo):
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE)
    
    def __str__(self):
        return f'Estadística de {self.equipo.nombre} en {self.campeonato.nombre}'

class EstadisticaPartido(models.Model):
    partido = models.OneToOneField('Partido',on_delete=models.CASCADE)
    minutos_agregados = models.IntegerField(default=0)
    tiros_esquina = models.IntegerField(default=0)
    tiros_libres = models.IntegerField(default=0)

    def __str__(self):
        return f'Estadisticas de {self.partido.__str__()}'

class EstadisticaGlobalJugador(models.Model):
    jugador = models.ForeignKey('Jugador',on_delete=models.CASCADE)
    def __str__(self):
        return f'Estadística de {self.jugador.nombre}'

class EstadisticaCampeonatoJugador(EstadisticaGlobalJugador):
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE)
    def __str__(self):
        return f'Estadística de {self.jugador.nombre} en {self.campeonato.nombre}'
