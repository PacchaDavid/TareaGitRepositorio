# Generated by Django 5.0.6 on 2024-07-08 22:17

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='PlacaControladora',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50)),
                ('family', models.CharField(max_length=50)),
                ('producer', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Usuario',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('username', models.CharField(max_length=50)),
                ('password', models.CharField(max_length=50)),
                ('key', models.CharField(max_length=5)),
            ],
        ),
        migrations.CreateModel(
            name='BrazoRobotico',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50)),
                ('placa', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='gestionBrazoRobotico.placacontroladora')),
            ],
        ),
        migrations.CreateModel(
            name='Sensor',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50)),
                ('brazo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='gestionBrazoRobotico.brazorobotico')),
            ],
        ),
        migrations.CreateModel(
            name='Servomotor',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('model', models.CharField(max_length=50)),
                ('traction', models.CharField(max_length=20)),
                ('brazo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='gestionBrazoRobotico.brazorobotico')),
            ],
        ),
    ]
