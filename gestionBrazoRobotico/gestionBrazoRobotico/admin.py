from django.contrib import admin
from .models import PlacaControladora, BrazoRobotico, Servomotor, Sensor, Usuario

admin.site.register(PlacaControladora)
admin.site.register(BrazoRobotico)
admin.site.register(Servomotor)
admin.site.register(Sensor)
admin.site.register(Usuario)
