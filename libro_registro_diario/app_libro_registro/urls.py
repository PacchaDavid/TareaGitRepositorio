from django.urls import path
from .views import *

urlpatterns = [
        path('', inicio, name='inicio'),
        path('agregar_fila/<int:tabla_id>', agregar_fila, name='agregar_fila'),
        path('libros_de_registro/', libros_de_registro, name='libros_de_registro'),
        path('agregar_tabla/', agregar_tabla, name='agregar_tabla'),
        path('ver_tabla/<int:id>/', ver_tabla, name='ver_tabla'),
        path('eliminar_tabla/<int:id>', eliminar_tabla,name='eliminar_tabla')
]