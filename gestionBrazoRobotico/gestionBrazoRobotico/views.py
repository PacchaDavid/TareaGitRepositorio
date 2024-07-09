from django.shortcuts import render, redirect
from .forms import CrearBrazoForm, CrearUsuarioForm
from .models import BrazoRobotico, PlacaControladora, Sensor, Servomotor, Usuario
import random
from rest_framework import viewsets
from .serializers import *

def home(request):
    return render(request,'index.html')

def about_us(request):
    return render(request,'aboutus.html')

def info(request):
    return render(request,'info.html')

def materials(request):
    return render(request,'materials.html')

def graphics(request):
    return render(request,'graphics.html')

def controls(request):
    return render(request,'controls.html')

def gestion(request):
    key = random.randint(10000,99999)

    if request.method == 'GET':
        return render(request,'gestion.html',{
            'userForm': CrearUsuarioForm(),
        })
    else:
        Usuario.objects.create(username=request.POST['username'],password=request.POST['password'],key=str(key))
        return redirect('/users/')

def usuarios(request):
    usuarios = list(Usuario.objects.all())
    return render(request,'users.html',{
        'users' : usuarios
    })

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