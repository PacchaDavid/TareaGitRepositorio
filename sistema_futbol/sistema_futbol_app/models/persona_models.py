from django.db import models
from .base import *
from .statistical_models import *

class Persona(models.Model):
    url_image = models.TextField()
    nombre = models.CharField(max_length=100)
    fecha_nacimiento = models.DateField()
    estatura = models.FloatField()
    peso = models.FloatField()
    SEXO_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Femenino'),
    ]

    class Meta:
        abstract = True

    def __str__(self):
        return self.nombre

class Jugador(Persona):
    fecha_debut = models.DateField()
    
class Arbitro(Persona):
    TIPOARBITRO_OPCIONES = [
        ('principal','Arbitro Principal'),
        ('asistente','Arbitro Asistente'),
        ('cuarto','Cuarto Arbitro'),
        ('de_video','Arbitro de Video'),
        ('asistente_de_video','Arbitro Asistente de Video'),
    ]
    tipo = models.CharField(
        max_length=50,
        choices=TIPOARBITRO_OPCIONES
    )
    estadistica_partido = models.ForeignKey('EstadisticaPartido',on_delete=models.CASCADE)

class DirectorTecnico(Persona):
    equipo = models.OneToOneField('Equipo',on_delete=models.CASCADE)

class JugadorDeEquipo(models.Model):
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    equipo = models.ForeignKey('Equipo',on_delete=models.CASCADE)
    numero = models.IntegerField()
    POSICION_OPCIONES = [
        ('portero','Portero'),
        ('lateral','Lateral'),
        ('libero','Líbero'),
        ('defensa_central','Defensa Central'),
        ('centrocampista','Centrocampista'),
        ('delantero','Delantero'),
        ('extremo_izquierdo','Extremo Izquierdo'),
        ('extremo_derecho','Extremo Derecho'),
        ('media_punta','Media Punta'),
        ('falso_nueve','Falso Nueve'),
    ]
    posicion = models.CharField(
        max_length=50,
        choices=POSICION_OPCIONES
    )

    def __str__(self):
        return self.jugador.nombre + ' - ' + self.equipo.nombre