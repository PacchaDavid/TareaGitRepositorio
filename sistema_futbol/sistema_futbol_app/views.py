from django.shortcuts import render
from .models import *

def home(request):
    return render(request,'index.html')

def campeonatos(request):
    return render(request,'campeonatos.html',{
        'campeonatos' : Campeonato.objects.all()
    })

def equipos(request,campeonato_id):
    equipos = Campeonato.objects.get(id=campeonato_id).equipos_participantes()
    context = {
        'equipos' : tuple(equipos),
        'campeonato' : Campeonato.objects.get(id=campeonato_id),
        'numero_equipos' : equipos.__len__()
    }
    return render(request,'equipos.html',context)

def ver_equipo(request,id):
    context = {
        'equipo' : Equipo.objects.get(id=id),
        'jugadores_equipo' : Equipo.objects.get(id=id).jugadores(),
        'dt' : DirectorTecnico.objects.get(equipo_id=id)
    }
    return render(request,'ver_equipo.html', context)

def ver_tabla(request,campeonato_id):
    tabla = TablaPosiciones.objects.get(campeonato_id=campeonato_id)
    rows = tabla.rows()
    return render(request,'ver_tabla.html',{
        'tabla' : tabla,
        'rows' : rows,
        'equipos_participantes' : rows.__len__()
    }) 

def partidos(request,tabla_id):
    tabla = TablaPosiciones.objects.get(id=tabla_id)
    partidos = tabla.partidos()
    return render(request,'partidos.html',{
        'partidos' : partidos,
        'tabla' : tabla,
        'numero_partidos' : partidos.__len__()
    })

def ver_partido(request,id):
    return render(request,'ver_partido.html',{
        'partido' : Partido.objects.get(id=id)
    })

def ver_estadistica_partido(request,id):
    estadistica_partido = EstadisticaPartido.objects.get(partido_id=id)
    goles = Gol.objects.filter(estadistica_partido_id=estadistica_partido.id)
    faltas = Falta.objects.filter(estadistica_partido_id=estadistica_partido.id)
    cambios = Falta.objects.filter(estadistica_partido_id=estadistica_partido.id)
    tarjetas = Tarjeta.objects.filter(estadistica_partido_id=estadistica_partido.id)
    arbitros = Arbitro.objects.filter(estadistica_partido_id=estadistica_partido.id)
    marcador = Marcador.objects.get(partido_id=id)

    return render(request,'ver_estadistica_partido.html',{
        'estadistica': estadistica_partido,
        'goles' : goles,
        'cambios' : cambios,
        'faltas' : faltas,
        'tarjetas' : tarjetas,
        'arbitros' : arbitros,
        'marcador' : marcador
    })

def ver_todos_los_equipos(request):
    return render(request,'equipos.html',{
        'equipos' : Equipo.objects.all()
    })

