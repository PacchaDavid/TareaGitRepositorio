from django.db import models
from .base import *
from .statistical_models import *

class Partido(models.Model):
    equipo1 = models.ForeignKey('Equipo',related_name='equipo1',on_delete=models.CASCADE)
    equipo2 = models.ForeignKey('Equipo',related_name='equipo2',on_delete=models.CASCADE)
    fecha = models.DateField()
    hora = models.CharField(max_length=5)
    finalizado = models.BooleanField(default=False)
    escenario = models.CharField(max_length=50)
    duracion_minutos = models.IntegerField()
    amistoso = models.BooleanField(default=False)

    def __str__(self):
        cadena = self.equipo1.abreviacion_nombre + ' vs ' + self.equipo2.abreviacion_nombre
        if not self.amistoso:
            return cadena
        else:
            return f'{cadena} - Amistoso'
    
class PartidoCampeonato(Partido):
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE)

class Marcador(models.Model):
    partido = models.OneToOneField(Partido,on_delete=models.CASCADE)
    goles_equipo1 = models.IntegerField(default=0)
    goles_equipo2 = models.IntegerField(default=0)

    def __str__(self):
        eq1 = self.partido.equipo1.abreviacion_nombre
        eq2 = self.partido.equipo2.abreviacion_nombre
        return f'{eq1} {self.goles_equipo1} : {self.goles_equipo2} {eq2}'

class EventoPartido(models.Model):
    estadistica_partido = models.ForeignKey('EstadisticaPartido',on_delete=models.CASCADE)
    estadistica_global_jugador = models.ForeignKey('EstadisticaGlobalJugador',on_delete=models.CASCADE)
    jugadorDeEquipo = models.ForeignKey('JugadorDeEquipo',on_delete=models.CASCADE)
    minuto = models.IntegerField(default=0)

    class Meta:
        abstract = True

class Tarjeta(EventoPartido):
    TIPOTARJETA_OPCIONES = [
        ('amarilla','Amarilla'),
        ('roja','Roja'),
    ]
    tipo = models.CharField(
        max_length=10,
        choices=TIPOTARJETA_OPCIONES
    )

    def __str__(self):
        return f'{self.tipo} para {self.jugador.nombre}'

class Gol(EventoPartido):
    estadistica_gobal_equipo = models.ForeignKey('EstadisticaGlobalEquipo', on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.jugadorDeEquipo.jugador.nombre} - {self.minuto}'"
    
class OtrosEventos(models.Model):
    estadistica_partido = models.ForeignKey('EstadisticaPartido',on_delete=models.CASCADE)

    class Meta:
        abstract = True

    
class Cambio(OtrosEventos):
    jugador_entra = models.ForeignKey('JugadorDeEquipo',related_name='jugador_entra',on_delete=models.CASCADE)
    jugador_sale = models.ForeignKey('JugadorDeEquipo',related_name='jugador_sale',on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return f'{self.jugador_entra.nombre} por {self.jugador_sale.nombre}'
    
class Falta(OtrosEventos):
    jugador_cometio = models.ForeignKey('JugadorDeEquipo',related_name='jugador_cometio',on_delete=models.CASCADE)
    jugador_recibio = models.ForeignKey('JugadorDeEquipo',related_name='jugador_recibio',on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return f'{self.jugador_cometio.nombre} cometió falta a {self.jugador_recibio.nombre}'