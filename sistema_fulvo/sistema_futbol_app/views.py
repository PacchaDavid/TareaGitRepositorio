from django.shortcuts import render
from .models import *

def inicio(request):
    return render(request,'index.html')
