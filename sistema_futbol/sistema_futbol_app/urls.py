from django.urls import path
from .views import *


urlpatterns = [
    path('',inicio,name='inicio'),
    path('campeonatos/',campeonatos,name='campeonatos'),
    path('equipos/<int:id>/',equipos,name='equipos'),
    path('tabla_posiciones/<int:id>/',tabla_posiciones,name='tabla_posiciones'),
    path('ver_equipo/<int:id>/',ver_equipo,name='ver_equipo'),
    path('partidos/<int:id>/',partidos,name='partidos'),
    path('ver_partido/<int:id>/',ver_partido,name='ver_partido')
]