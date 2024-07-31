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
        max_length = 20,
        choices = MODALIDAD_OPCIONES,
        blank = True
    )


    def __str__(self):
        return self.nombre
    
class Partido(models.Model):
    equipo1 = models.ForeignKey('Equipo', related_name='equipo1', on_delete=models.CASCADE)
    equipo2 = models.ForeignKey('Equipo', related_name='equipo2', on_delete=models.CASCADE)
    fecha = models.DateField()
    hora = models.CharField(max_length=5)
    finalizado = models.BooleanField(default=False)
    escenario = models.CharField(max_length=50)
    duracion = models.CharField(max_length=50)
    amistoso = models.BooleanField(default=False)

    def __str__(self):
        equipo1String = self.equipo1.nombre
        equipo2String = self.equipo2.nombre
        return equipo1String + " vs " + equipo2String

class Marcador(models.Model):
    partido = models.OneToOneField('Partido', on_delete=models.CASCADE)
    goles_equipo_1 = models.IntegerField()
    goles_equipo_2 = models.IntegerField()

    def __str__(self):
        return self.partido

class RowTablaPosiciones(models.Model):
    posicion = models.IntegerField(blank=True)
    equipo = models.OneToOneField('Equipo', on_delete=models.CASCADE)
    puntos = models.IntegerField(default=0)
    goles_a_favor = models.IntegerField(default=0)
    goles_en_contra = models.IntegerField(default=0)
    diferencia_de_goles = models.IntegerField(default=0)
    partidos_jugados = models.IntegerField(default=0)
    partidos_ganados = models.IntegerField(default=0)
    partidos_empatados = models.IntegerField(default=0)
    partidos_perdidos = models.IntegerField(default=0)
    TablaPosiciones = models.ForeignKey('TablaPosiciones', on_delete=models.CASCADE)

class Equipo(models.Model):
    nombre = models.CharField(max_length=50)
    jugadores = models.ManyToManyField('Jugador')

    def __str__(self):
        return self.nombre


class Jugador(models.Model):
    SEXO_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Femenino'),
    ]
    nombre = models.CharField(max_length=50)
    edad = models.IntegerField()
    numero = models.IntegerField()
    sexo = models.CharField(
        max_length = 10,
        choices = SEXO_OPCIONES,
        blank = True
    )
    def __str__(self):
        return self.nombre 


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

    def __str__(self):
        return self.nombre

class Patrocinador(models.Model):
    nombre = models.CharField(max_length=50)
    url = models.CharField(max_length=100)
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE, blank=True)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE, blank=True)
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE, blank=True)

class EstadisticaPartido(models.Model):
    partido = models.OneToOneField(Partido,on_delete=models.CASCADE)
    tiros_esquina = models.IntegerField()
    tiros_libre = models.IntegerField()

    tiros_penales = models.IntegerField()
    minutos_agregados = models.IntegerField()
    jugadores = models.ManyToManyField(Jugador)
    arbitros = models.ManyToManyField(Arbitro)



class Cambio(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador_entra = models.ForeignKey(Jugador, related_name='jugador_entra',on_delete=models.CASCADE)
    jugador_sale = models.ForeignKey(Jugador, related_name='jugador_sale',on_delete=models.CASCADE)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.jugador_entra + " por " + self.jugador_sale
    
class Gol(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    minuto = models.IntegerField()
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    en_contra = models.BooleanField(default=False)

    def __str__(self):
        return self.jugador + " '" + str(self.minuto)
    
class Falta(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador_cometio = models.ForeignKey(Jugador, related_name='jugador_cometio', on_delete=models.CASCADE)
    jugador_recibio = models.ForeignKey(Jugador, related_name='jugador_recibio', on_delete=models.CASCADE)
    equipo_jugador_cometio = models.ForeignKey(Equipo, related_name='equipo_jugador_cometio', on_delete=models.CASCADE)
    equipo_jugador_recibio = models.ForeignKey(Equipo, related_name='equipo_jugador_recibio', on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.jugador_cometio

class Tarjeta(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
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
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.tipo_tarjeta

class EstadisticaJugador(models.Model):
    jugador = models.OneToOneField(Jugador,on_delete=models.CASCADE)
    precision_pases = models.FloatField()
    

class EstadisticaEquipo(models.Model):
    efectividad = models.FloatField()
    posesion = models.FloatField()
    goles = models.IntegerField()
    mejor_puntuacion = models.FloatField()

class TablaPosiciones(models.Model):
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE)

    def __str__(self):
        return "Tabla de Posiciones - " + self.campeonato.nombre
    









