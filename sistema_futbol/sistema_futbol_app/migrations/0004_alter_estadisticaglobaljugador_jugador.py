# Generated by Django 5.0.7 on 2024-08-02 22:07

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('sistema_futbol_app', '0003_alter_estadisticapartido_minutos_agregados_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='estadisticaglobaljugador',
            name='jugador',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='sistema_futbol_app.jugador'),
        ),
    ]