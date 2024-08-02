from django.urls import path
from .views import *

urlpatterns = [
    path('',home,name='home'),
    path('campeonatos/',campeonatos,name='campeonatos'),
    path('equipos/<int:campeonato_id>/',equipos,name='equipos'),
    path('ver_equipo/<int:id>/',ver_equipo,name='ver_equipo'),
    path('ver_tabla/<int:campeonato_id>/',ver_tabla,name='ver_tabla'),
    path('partidos/<int:tabla_id>/',partidos,name='partidos'),
    path('ver_partido/<int:id>/',ver_partido,name='ver_partido'),  
    path('ver_estadistica_partido/<int:id>/',ver_estadistica_partido,name='ver_estadistica_partido'),
    path('ver_todos_los_equipos/',ver_todos_los_equipos,name='ver_todos_los_equipos')
]