from django.db import models

class PlacaControladora(models.Model):
    name = models.CharField(max_length=50)
    family = models.CharField(max_length=50)
    producer = models.CharField(max_length=50)
    def __str__(self):
        return self.name + ' - ' + self.producer

class BrazoRobotico(models.Model):
    name = models.CharField(max_length=50)
    placa = models.ForeignKey(PlacaControladora,on_delete=models.CASCADE)

    def __str__(self):
        return self.name

class Servomotor(models.Model):
    model = models.CharField(max_length=50)
    traction = models.CharField(max_length=20)
    brazo = models.ForeignKey(BrazoRobotico,on_delete=models.CASCADE)
    def __str__(self):
        return self.model

class Sensor(models.Model):
    name = models.CharField(max_length=50)
    brazo = models.ForeignKey(BrazoRobotico,on_delete=models.CASCADE)
    def __str__(self):    
        return self.name
    
class Usuario(models.Model):
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    key = models.CharField(max_length=5)
    def __str__(self):
        return self.username
