# Generated by Django 5.0.6 on 2024-07-31 23:19

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Arbitro',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('edad', models.IntegerField()),
                ('tipo', models.CharField(blank=True, choices=[('principal', 'Arbitro Principal'), ('asistente', 'Arbitro Asistente'), ('cuarto_arbitro', 'Cuarto Arbitro'), ('de_video', 'Arbitro de Video (VAR)')], max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Campeonato',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('premios', models.TextField()),
                ('descripcion', models.TextField()),
                ('fecha_inicio', models.DateField()),
                ('fecha_fin', models.DateField()),
                ('finalizado', models.BooleanField(default=False)),
                ('sede', models.CharField(max_length=100)),
                ('categoria', models.CharField(blank=True, choices=[('masculino', 'Masculino'), ('femenino', 'Femenino'), ('mixto', 'Mixto')], max_length=10)),
                ('modalidad', models.CharField(blank=True, choices=[('liga', 'Liga'), ('eliminacion_directa', 'Eliminacion Directa'), ('copa', 'Copa'), ('grupos', 'Grupos')], max_length=20)),
            ],
        ),
        migrations.CreateModel(
            name='Equipo',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=100)),
                ('abreviacion_nombre', models.CharField(max_length=10)),
                ('url_image', models.TextField()),
            ],
        ),
        migrations.CreateModel(
            name='EstadisticaEquipo',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('efectividad', models.FloatField()),
                ('posesion', models.FloatField()),
                ('goles', models.IntegerField()),
                ('mejor_puntuacion', models.FloatField()),
            ],
        ),
        migrations.CreateModel(
            name='Jugador',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('url_image', models.TextField()),
                ('nombre', models.CharField(max_length=50)),
                ('edad', models.IntegerField()),
                ('sexo', models.CharField(blank=True, choices=[('masculino', 'Masculino'), ('femenino', 'Femenino')], max_length=10)),
                ('nacionalidad', models.CharField(max_length=50)),
                ('estatura', models.FloatField()),
            ],
        ),
        migrations.CreateModel(
            name='DirectorTecnico',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('edad', models.IntegerField()),
                ('equipo', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
            ],
        ),
        migrations.CreateModel(
            name='EstadisticaPartido',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tiros_esquina', models.IntegerField()),
                ('tiros_libre', models.IntegerField()),
                ('tiros_penales', models.IntegerField()),
                ('minutos_agregados', models.IntegerField()),
                ('campeonato', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.campeonato')),
            ],
        ),
        migrations.CreateModel(
            name='Gol',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('minuto', models.IntegerField()),
                ('en_contra', models.BooleanField(default=False)),
                ('equipo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('estadistica_partido', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.estadisticapartido')),
                ('jugador', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='Falta',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('minuto', models.IntegerField()),
                ('equipo_jugador_cometio', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='equipo_jugador_cometio', to='sistema_futbol_app.equipo')),
                ('equipo_jugador_recibio', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='equipo_jugador_recibio', to='sistema_futbol_app.equipo')),
                ('estadistica_partido', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.estadisticapartido')),
                ('jugador_cometio', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='jugador_cometio', to='sistema_futbol_app.jugador')),
                ('jugador_recibio', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='jugador_recibio', to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='EstadisticaJugador',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('jugador', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='Cambio',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('minuto', models.IntegerField()),
                ('equipo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('estadistica_partido', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.estadisticapartido')),
                ('jugador_entra', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='jugador_entra', to='sistema_futbol_app.jugador')),
                ('jugador_sale', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='jugador_sale', to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='JugadorDeEquipo',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('numero', models.IntegerField()),
                ('posicion', models.CharField(blank=True, choices=[('portero', 'Portero'), ('lateral', 'Lateral'), ('carrilero', 'Carrilero'), ('libero', 'Líbero'), ('defensa_central', 'Defensa central'), ('centroCampista', 'Centrocampista'), ('delantero', 'Delantero'), ('extremo_izquierdo', 'Extremo izquierdo'), ('extremo_derecho', 'Extremo derecho'), ('media_punta', 'Media punta'), ('falso_9', 'Falso nueve')], max_length=50)),
                ('equipo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('jugador', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='Partido',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fecha', models.DateField()),
                ('hora', models.CharField(max_length=5)),
                ('finalizado', models.BooleanField(default=False)),
                ('escenario', models.CharField(max_length=50)),
                ('duracion', models.CharField(max_length=50)),
                ('amistoso', models.BooleanField(default=False)),
                ('equipo1', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='equipo1', to='sistema_futbol_app.equipo')),
                ('equipo2', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='equipo2', to='sistema_futbol_app.equipo')),
            ],
        ),
        migrations.CreateModel(
            name='Marcador',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('goles_equipo_1', models.IntegerField()),
                ('goles_equipo_2', models.IntegerField()),
                ('partido', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.partido')),
            ],
        ),
        migrations.AddField(
            model_name='estadisticapartido',
            name='partido',
            field=models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.partido'),
        ),
        migrations.CreateModel(
            name='Patrocinador',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=50)),
                ('url_image', models.TextField()),
                ('campeonato', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.campeonato')),
                ('equipo', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('jugador', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador')),
            ],
        ),
        migrations.CreateModel(
            name='TablaPosiciones',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('campeonato', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.campeonato')),
            ],
        ),
        migrations.CreateModel(
            name='RowTablaPosiciones',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('posicion', models.IntegerField(blank=True)),
                ('puntos', models.IntegerField(default=0)),
                ('goles_a_favor', models.IntegerField(default=0)),
                ('goles_en_contra', models.IntegerField(default=0)),
                ('diferencia_de_goles', models.IntegerField(default=0)),
                ('partidos_jugados', models.IntegerField(default=0)),
                ('partidos_ganados', models.IntegerField(default=0)),
                ('partidos_empatados', models.IntegerField(default=0)),
                ('partidos_perdidos', models.IntegerField(default=0)),
                ('equipo', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('TablaPosiciones', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.tablaposiciones')),
            ],
        ),
        migrations.CreateModel(
            name='Tarjeta',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tipo_tarjeta', models.CharField(blank=True, choices=[('roja', 'Roja'), ('amarilla', 'Amarilla')], max_length=10)),
                ('minuto', models.IntegerField()),
                ('equipo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.equipo')),
                ('estadistica_partido', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.estadisticapartido')),
                ('jugador', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador')),
            ],
        ),
    ]
