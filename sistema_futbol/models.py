from django.db import models

class Campeonato(models.Model):
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
    
class Persona(models.Model):
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

class Patrocinador(models.Model):
    nombre = models.CharField(max_length=50)
    equipo = models.ManyToManyField(blank=True,null=True)
    campeonato = models.ManyToManyField(blank=True,null=True)
    jugador = models.ManyToManyField(blank=True,null=True)

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
    equipo = models.OneToOneField('Equipo')

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



class Equipo(models.Model):
    nombre = models.CharField(max_length=100)
    abreviacion_nombre = models.CharField(max_length=7)

    def __str__(self):
        return self.nombre

""" class Patrocinador(models.Model):
    nombre = models.CharField(max_length=50)
    equipo = models.ManyToManyField(blank=True,null=True)
    campeonato = models.ManyToManyField(blank=True,null=True)
    jugador = models.ManyToManyField(blank=True,null=True)

    def __str__(self):
        return self.nombre """



class Partido(models.Model):
    equipo1 = models.ForeignKey(Equipo,related_name='equipo1',on_delete=models.CASCADE)
    equipo2 = models.ForeignKey(Equipo,related_name='equipo2',on_delete=models.CASCADE)
    fecha = models.DateField()
    hora = models.CharField(max_length=5)
    finalizado = models.BooleanField(default=False)
    escenario = models.CharField(max_length=50)
    duracion_minutos = models.IntegerField()

    def __str__(self):
        return self.equipo1.abreviacion_nombre + ' vs ' + self.equipo2.abreviacion_nombre
    
class PartidoCampeonato(Partido):
    campeonato = models.ForeignKey('Campeonato',on_delete=models.CASCADE)

class Marcador(models.Model):
    partido = models.OneToOneField(Partido)
    goles_equipo1 = models.IntegerField()
    goles_equipo2 = models.IntegerField()

    def __str__(self):
        eq2 = self.partido.equipo1.abreviacion_nombre
        eq1 = self.partido.equipo2.abreviacion_nombre
        return f'{eq1} {self.goles_equipo1} : {self.goles_equipo2} {eq2}'

class EventoPartido(models.Model):
    estadistica_partido = models.ForeignKey('EstadisticaPartido',on_delete=models.CASCADE)
    estadistica_jugador = models.ForeignKey('EstadisticaJugador',on_delete=models.CASCADE)
    estadistica_partido = models
    jugador = models.ForeignKey(Jugador,on_delete=models.CASCADE)
    minuto = models.IntegerField

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
    estadistica_campeonato_equipo = models.ForeignKey('EstadisticaCampeonatoEquipo',on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.jugador.nombre} - {self.minuto}'"
    
class OtrosEventos(models.Model):
    estadistica_partido = models.ForeignKey('EstadisticaPartido',on_delete=models.CASCADE)

class Cambio(OtrosEventos):
    jugador_entra = models.ForeignKey(Jugador,related_name='jugador_entra',on_delete=models.CASCADE)
    jugador_sale = models.ForeignKey(Jugador,related_name='jugador_sale',on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return f'{self.jugador_entra.nombre} por {self.jugador_sale.nombre}'
    
class Falta(OtrosEventos):
    jugador_cometio = models.ForeignKey(Jugador,related_name='jugador_cometio',on_delete=models.CASCADE)
    jugador_recibio = models.ForeignKey(Jugador,related_name='jugador_recibio',on_delete=models.CASCADE)
    minuto = models.IntegerField()

    def __str__(self):
        return f'{self.jugador_cometio.nombre} cometió falta a {self.jugador_recibio.nombre}'



class EstadisticaGlobalEquipo(models.Model):
    equipo = models.ForeignKey(Equipo,on_delete=models.CASCADE)

    def __str__(self):
        return f'Estadística global de {self.equipo.nombre}'

class EstadisticaCampeonatoEquipo(EstadisticaGlobalEquipo):
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE)
    
    def __str__(self):
        return f'Estadística de {self.equipo.nombre} en {self.campeonato.nombre}'

class EstadisticaPartido(models.Model):
    partido = models.OneToOneField(Partido)
    minutos_agregados = models.IntegerField()
    tiros_esquina = models.IntegerField()
    tiros_libres = models.IntegerField()

    def __str__(self):
        return f'Estadistica de {self.partido.__str__()}'

class EstadisticaGlobalJugador(models.Model):
    jugador = models.OneToOneField(Jugador)
    def __str__(self):
        return f'Estadística de {self.jugador.nombre} en {self.campeonato.nombre}'

class EstadisticaCampeonatoJugador(EstadisticaGlobalJugador):
    campeonato = models.ForeignKey(Campeonato,on_delete=models.CASCADE)
    def __str__(self):
        return f'Estadística de {self.jugador.nombre} en {self.campeonato.nombre}'

