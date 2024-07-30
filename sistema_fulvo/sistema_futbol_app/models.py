from django.db import models

class Campeonato(models.Model):
    nombre = models.CharField(max_length=50)
    premios = models.TextField()
    descripcion = models.TextField()
    fecha_inicio = models.DateField()
    fecha_fin = models.DateField()
    finalizado = models.BooleanField(default=False)

    CATEGORIA_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Femenino'),
        ('mixto','Mixto'),
    ]
    MODALIDAD_OPCIONES = [
        ('liga','Liga'),
        ('eliminacion_directa','Eliminacion Directa'),
        ('copa','Copa'),
        ('grupos','Grupos'),
    ]
    categoria = models.CharField(
        max_length = 10,
        choices = CATEGORIA_OPCIONES,
        blank = True
    )
    modalidad = models.CharField(
        max_length = 10,
        choices = MODALIDAD_OPCIONES,
        blank = True
    )


    def __str__(self):
        return self.nombre
    
class Partido(models.Model):
    equipo1 = models.ForeignKey('Equipo', on_delete=models.CASCADE)
    equipo2 = models.ForeignKey('Equipo', on_delete=models.CASCADE)
    fecha = models.DateField()
    hora = models.CharField(max_length=5)
    finalizado = models.BooleanField(default=False)
    escenario = models.CharField(max_length=50)
    duracion = models.CharField(max_length=50)
    marcador = models.OneToOneField('Marcador', on_delete=models.CASCADE)
    tabla_posiciones = models.ForeignKey('TablaPosiciones',on_delete=models.CASCADE)

class Marcador(models.Model):
    equipo1 = models.ForeignKey('Equipo', on_delete=models.CASCADE)
    equipo2 = models.ForeignKey('Equipo', on_delete=models.CASCADE)
    goles_equipo_1 = models.IntegerField()
    goles_equipo_2 = models.IntegerField()

    def __str__(self):
        return self.partido

class RowTablaPosiciones(models.Model):
    equipo = models.OneToOneField('Equipo', on_delete=models.CASCADE)
    puntos = models.IntegerField()
    goles_a_favor = models.IntegerField()
    goles_en_contra = models.IntegerField()
    diferencia_de_goles = models.IntegerField()
    
class Equipo(models.Model):
    nombre = models.CharField(max_length=50)
    jugadores = models.ManyToManyField('Jugador')


class Jugador(models.Model):
    SEXO_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Sexo'),
    ]
    nombre = models.charField(max_length=50)
    edad = models.IntegerField()
    numero = models.IntegerField()

class Arbitro(models.Model):
    nombre = models.CharField(max_length=50)
    edad = models.IntegerField()
    TIPOARBITRO_OPCIONES = [
        ('principal','Arbitro Principal'),
        ('asistente','Arbitro Asistente'),
        ('cuarto_arbitro','Cuarto Arbitro'),
        ('de_video','Arbitro de Video (VAR)'),
    ]

    tipo = models.CharField(
        max_length=50,
        choices=TIPOARBITRO_OPCIONES,
        blank=True
    )

class Patrocinador(models.Model):
    nombre = models.CharField(max_length=50)
    url = models.charField(max_lenght=50)
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)

class EstadisticaPartido(models.Model):
    tiros_esquina = models.IntegerField()
    goles = models.IntegerField()
    tiros_penales = models.IntegerField()
    minutos_agregados = models.IntegerField()

class Tarjetas(models.Model):
    TIPOTARJETA_OPCIONES = [
        ('roja','Roja'),
        ('amarilla','Amarilla'),
    ]
    tipo_tarjeta = models.CharField(
        max_length=10,
        choices=TIPOTARJETA_OPCIONES,
        blank=True
    )
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.tipo_tarjeta

class EstadisticaJugador(models.Model):
    jugador = models.OneToOneField(Jugador,on_delete=models.CASCADE)
    precision_pases = models.FloatField()
    goles = models.IntegerField()
    
""" Sugerencias:
    - en EstadisticaPartido agregar jugadores que participaron, ingresaron y salieron
    - se puede generar una clase Cambio para agregar los jugadores relacionados
    - añadir clase Gol con atributos de jugador y minuto
    - clase Falta con los jugadores implicados y el minuto
    - Calcular efectividad del equipo de acuerdo a varios parámetros"""

class EstadisticaEquipo(models.Model):
    efectividad = models.FloatField()









