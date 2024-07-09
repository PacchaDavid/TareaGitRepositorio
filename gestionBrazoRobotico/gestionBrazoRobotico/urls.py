from django.urls import path, include
from .views import *
from . import views
from rest_framework import routers

router = routers.DefaultRouter()
router.register(r'brazos_roboticos',BrazosViewSet)
router.register(r'usuarios', UsuariosViewSet)
router.register(r'placas',PlacasViewSet)
router.register(r'servos',ServosViewSet)
router.register(r'sensors',SensorsViewSet)

urlpatterns = [
    path('',views.home,name='home'),
    path('aboutus/', views.about_us,name='about_us'),
    path('info/',views.info,name='context'),
    path('materials/',views.materials,name='materials'),
    path('graphics/',views.graphics,name='graphics'),
    path('controls/',views.controls,name='controls'),
    path('gestion/',views.gestion,name='gestion'),
    path('users/',views.usuarios,name='usuarios'),
    path('api/', include(router.urls))
]