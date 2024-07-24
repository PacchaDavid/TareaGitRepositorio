from django.shortcuts import render, redirect
from .forms import CrearBrazoForm, CrearUsuarioForm
from .models import BrazoRobotico, PlacaControladora, Sensor, Servomotor, Usuario
import random
from rest_framework import viewsets
from .serializers import *
from . import services


def home(request):
    return render(request,'principal/index.html')

def about_us(request):
    return render(request,'principal/aboutus.html')

def info(request):
    return render(request,'principal/info.html')

def materials(request):
    return render(request,'principal/materials.html')

def graphics(request):
    return render(request,'principal/graphics.html')

def controls(request):
    return render(request,'controls/controls.html')

def gestion(request):
    key = random.randint(10000,99999)

    if request.method == 'GET':
        return render(request,'principal/gestion.html',{
            'userForm': CrearUsuarioForm(),
        })
    else:
        Usuario.objects.create(username=request.POST['username'],password=request.POST['password'],key=str(key))
        return redirect('/users/')

def usuarios(request):
    usuarios = list(Usuario.objects.all())
    return render(request,'principal/users.html',{
        'users' : usuarios
    })

def random_user(request):
    return render(request,'principal/randomuser.html',services.get_random_user())

def chiste_del_dia(request):
    return render(request,'principal/chiste.html',services.get_chiste())

def control2(request):
    return render(request,'controls/controls2.html')

class UsuariosViewSet(viewsets.ModelViewSet):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer

class BrazosViewSet(viewsets.ModelViewSet):
    queryset = BrazoRobotico.objects.all()
    serializer_class = BrazoSerializer

class PlacasViewSet(viewsets.ModelViewSet):
    queryset = PlacaControladora.objects.all()
    serializer_class = PlacaSerializer

class ServosViewSet(viewsets.ModelViewSet):
    queryset = Servomotor.objects.all()
    serializer_class = ServoSerializer

class SensorsViewSet(viewsets.ModelViewSet):
    queryset = Sensor.objects.all()
    serializer_class = SensorSerializer