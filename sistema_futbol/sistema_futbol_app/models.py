from django.db import models

class Campeonato(models.Model):
    nombre = models.CharField(max_length=50)
    premios = models.TextField()
    descripcion = models.TextField()
    fecha_inicio = models.DateField()
    fecha_fin = models.DateField()
    finalizado = models.BooleanField(default=False)
    sede = models.CharField(max_length=100)

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
        return self.equipo1.abreviacion_nombre + ' vs ' + self.equipo2.abreviacion_nombre

class Marcador(models.Model):
    partido = models.OneToOneField('Partido', on_delete=models.CASCADE)
    goles_equipo_1 = models.IntegerField()
    goles_equipo_2 = models.IntegerField()

    def __str__(self):
        return self.partido.equipo1.nombre + ' ' + str(self.goles_equipo_1) + ' : ' + str(self.goles_equipo_2) + ' ' + self.partido.equipo2.nombre

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

    def __str__(self):
        return self.equipo.abreviacion_nombre + ' - ' + self.TablaPosiciones.campeonato.nombre

class Equipo(models.Model):
    nombre = models.CharField(max_length=100)
    abreviacion_nombre = models.CharField(max_length=10)
    url_image = models.TextField()

    def __str__(self):
        return self.nombre


class Jugador(models.Model):
    SEXO_OPCIONES = [
        ('masculino','Masculino'),
        ('femenino','Femenino'),
    ]
    url_image = models.TextField()
    nombre = models.CharField(max_length=50)
    edad = models.IntegerField()
    sexo = models.CharField(
        max_length = 10,
        choices = SEXO_OPCIONES,
        blank = True
    )
    nacionalidad = models.CharField(max_length=50)
    estatura = models.FloatField()

    def __str__(self):
        return self.nombre 
    
class JugadorDeEquipo(models.Model):
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    numero = models.IntegerField()
    POSICION_OPCIONES = [
        ('portero','Portero'),
        ('lateral','Lateral'),
        ('carrilero','Carrilero'),
        ('libero','Líbero'),
        ('defensa_central','Defensa central'),
        ('centroCampista','Centrocampista'),
        ('delantero','Delantero'),
        ('extremo_izquierdo','Extremo izquierdo'),
        ('extremo_derecho','Extremo derecho'),
        ('media_punta','Media punta'),
        ('falso_9','Falso nueve'),
    ]
    posicion = models.CharField(
        max_length=50,
        choices=POSICION_OPCIONES,
        blank=True
    )

    def __str__(self):
        return self.jugador.nombre + ' - ' + self.equipo.abreviacion_nombre

class DirectorTecnico(models.Model):
    nombre = models.CharField(max_length=50)
    edad = models.IntegerField()
    equipo = models.OneToOneField(Equipo,on_delete=models.CASCADE)

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
    url_image = models.TextField()
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE, blank=True, null=True)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE, blank=True, null=True)
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE, blank=True, null=True)

    def __str__(self):
        return self.nombre

class EstadisticaPartido(models.Model):
    partido = models.OneToOneField(Partido,on_delete=models.CASCADE)
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE, blank=True, null=True)
    tiros_esquina = models.IntegerField()
    tiros_libre = models.IntegerField()
    tiros_penales = models.IntegerField()
    minutos_agregados = models.IntegerField()

    def __str__(self):
        return self.partido.__str__()

class Cambio(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador_entra = models.ForeignKey(Jugador, related_name='jugador_entra',on_delete=models.CASCADE)
    jugador_sale = models.ForeignKey(Jugador, related_name='jugador_sale',on_delete=models.CASCADE)
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.jugador_entra.__str__() + " por " + self.jugador_sale.__str__()
    
class Gol(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    minuto = models.IntegerField()
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)
    en_contra = models.BooleanField(default=False)

    def __str__(self):
        return self.jugador.nombre + " '" + str(self.minuto)
    
class Falta(models.Model):
    estadistica_partido = models.ForeignKey(EstadisticaPartido,on_delete=models.CASCADE)
    jugador_cometio = models.ForeignKey(Jugador, related_name='jugador_cometio', on_delete=models.CASCADE)
    jugador_recibio = models.ForeignKey(Jugador, related_name='jugador_recibio', on_delete=models.CASCADE)
    equipo_jugador_cometio = models.ForeignKey(Equipo, related_name='equipo_jugador_cometio', on_delete=models.CASCADE)
    equipo_jugador_recibio = models.ForeignKey(Equipo, related_name='equipo_jugador_recibio', on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return self.jugador_cometio.nombre + " cometio falta a " + self.jugador_recibio.nombre

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
        return self.tipo_tarjeta + " para " + self.jugador.nombre

class EstadisticaJugador(models.Model):
    jugador = models.OneToOneField(Jugador,on_delete=models.CASCADE)

    def __str__(self):
        return 'Estadistica de ' + self.jugador.nombre
    

class EstadisticaEquipo(models.Model):
    efectividad = models.FloatField()
    posesion = models.FloatField()
    goles = models.IntegerField()
    mejor_puntuacion = models.FloatField()

class TablaPosiciones(models.Model):
    campeonato = models.OneToOneField(Campeonato,on_delete=models.CASCADE)

    def __str__(self):
        return "Tabla de Posiciones - " + self.campeonato.nombre
    