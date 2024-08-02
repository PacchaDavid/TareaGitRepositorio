from django.db import models
from .persona_models import Jugador, JugadorDeEquipo
from .partido_models import *

class Equipo(models.Model):
    url_image = models.TextField()
    nombre = models.CharField(max_length=100)
    abreviacion_nombre = models.CharField(max_length=7)

    def __str__(self):
        return self.nombre
    
    def jugadores(self):
        return JugadorDeEquipo.objects.filter(equipo_id=self.id)
    
class Campeonato(models.Model):
    url_image = models.TextField()
    nombre = models.CharField(max_length=50)
    descripcion = models.TextField()
    fecha_inicio = models.DateField()
    fecha_fin = models.DateField()
    finalizado = models.BooleanField(default=False)
    sede = models.CharField(max_length=50)
    CATEGORIA_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Femenino'),
        ('mixto','Mixto'),
    ]
    MODALIDAD_OPCIONES = [
        ('liga','Liga'),
        ('eliminacion_directa','Eliminacion directa'),
        ('copa','Copa'),
        ('combinado','Liga y eliminación directa'),
        ('grupos','Fase de Grupos y Playoffs'),
        ('ascenso_y_descenso','Ascenso y Descenso'),    
    ]
    categoria = models.CharField(
        max_length=60,
        choices=CATEGORIA_OPCIONES,
    )
    modalidad = models.CharField(
        max_length=60,
        choices=MODALIDAD_OPCIONES
    )

    def __str__(self):
        return self.nombre
    
    def equipos_participantes(self):
        tabla = TablaPosiciones.objects.get(campeonato_id=self.id)
        rows = RowTablaPosiciones.objects.filter(tabla_posiciones_id=tabla.id)
        return [row.equipo for row in rows]

class Patrocinador(models.Model):
    nombre = models.CharField(max_length=50)
    equipo = models.ForeignKey('Equipo',on_delete=models.CASCADE,blank=True,null=True)
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE,blank=True,null=True)
    jugador = models.ForeignKey('Jugador',on_delete=models.CASCADE,blank=True,null=True)

    def __str__(self):
        return self.nombre
    
class TablaPosiciones(models.Model):
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE)

    def __str__(self):
        return f'Tabla de posiciones: {self.campeonato.nombre}'

    def rows(self):
        return RowTablaPosiciones.objects.filter(tabla_posiciones_id=self.id)
    
    def partidos(self):
        return PartidoCampeonato.objects.filter(campeonato_id=self.campeonato.id)

class RowTablaPosiciones(models.Model):
    tabla_posiciones = models.ForeignKey('TablaPosiciones',on_delete=models.CASCADE)
    equipo = models.ForeignKey('Equipo',on_delete=models.CASCADE)
    posicion = models.IntegerField(default=1)
    puntos = models.IntegerField(default=0)
    gf = models.IntegerField(default=0)
    gc = models.IntegerField(default=0)
    dg = models.IntegerField(default=0)
    victorias = models.IntegerField(default=0)
    derrotas = models.IntegerField(default=0)
    empates = models.IntegerField(default=0)

    def __str__(self):
        return f'{self.equipo.nombre} en {self.tabla_posiciones.campeonato.nombre}'