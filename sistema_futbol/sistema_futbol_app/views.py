from django.shortcuts import render
from .models import *

def inicio(request):
    return render(request,'index.html')

def campeonatos(request):
    return render(request,'campeonatos.html',{'campeonatos':Campeonato.objects.all()})

def equipos(request, id):
    tabla_posiciones = TablaPosiciones.objects.get(campeonato_id=id)
    tabla_rows = RowTablaPosiciones.objects.filter(TablaPosiciones_id=tabla_posiciones.id)
    tabla_completa = {
        'tabla_posiciones':tabla_posiciones,
        'tabla_rows':tabla_rows
    }
    for row in tabla_completa['tabla_rows']:    
        print(row)

    return render(request,'equipos.html',{'tabla_completa':tabla_completa})

def tabla_posiciones(request,id):
    tabla_posiciones = TablaPosiciones.objects.get(campeonato_id=id)
    tabla_rows = RowTablaPosiciones.objects.filter(TablaPosiciones_id=tabla_posiciones.id)
    tabla_completa = {
        'tabla_posiciones':tabla_posiciones,
        'tabla_rows':tabla_rows
    }
    for row in tabla_completa['tabla_rows']:    
        print(row)

    return render(request,'tabla_posiciones.html',{'tabla_completa':tabla_completa})

def partidos(request,id):
    partidos = list(EstadisticaPartido.objects.filter(campeonato_id=id))

    return render(request,'partidos.html',{'partidos':partidos})

def ver_equipo(request, id):
    equipo = Equipo.objects.get(id=id)
    jugadores = list(JugadorDeEquipo.objects.filter(equipo_id=id))
    partidos = list(Partido.objects.filter(equipo1_id=id))
    for partido in list(Partido.objects.filter(equipo2_id=id)):
        partidos.append(partido)

    print(partidos)
    equipo_completo = {
        'equipo' : equipo,
        'jugadores' : jugadores,
        'dt' : DirectorTecnico.objects.get(equipo_id=id),
        'partidos' : partidos
    }
    return render(request,'ver_equipo.html',{'equipo_completo' : equipo_completo})


def ver_partido(request,id):
    return render(request,'ver_partido.html',{'partido' : Partido.objects.get(id=id)})