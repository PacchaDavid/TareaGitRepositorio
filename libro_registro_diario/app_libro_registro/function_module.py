from .models import SubRow, RowTablaRegistro, TablaRegistro

def crear_objeto_sub_row(ingresos, egresos, saldo):
    auxiliar = 0
    if ingresos == None:
        auxiliar += 3
    if egresos == None:
        auxiliar += 4
    if saldo == None: 
        auxiliar += 5

    if auxiliar == 0:
        new = SubRow.objects.create(ingresos=ingresos,egresos=egresos,saldo=saldo)
        new.save()
    elif auxiliar == 3:
        new = SubRow.objects.create(ingresos=ingresos,egresos=egresos,saldo=saldo)
        new.save()
