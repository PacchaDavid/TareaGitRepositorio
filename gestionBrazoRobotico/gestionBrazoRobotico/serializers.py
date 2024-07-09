from rest_framework import serializers
from .models import *

class BrazoSerializer(serializers.ModelSerializer):
    class Meta:
        model = BrazoRobotico
        fields = ['name','placa']

class UsuarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = Usuario
        fields = '__all__'

class PlacaSerializer(serializers.ModelSerializer):
    class Meta:
        model = PlacaControladora
        fields = '__all__'

class ServoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Servomotor
        fields = '__all__'

class SensorSerializer(serializers.ModelSerializer):
    class Meta: 
        model = Sensor
        fields = '__all__'