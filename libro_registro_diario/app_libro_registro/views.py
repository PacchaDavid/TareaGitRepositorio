from django.shortcuts import render, redirect
from .models import TablaRegistro, SubRow, RowTablaRegistro
from .forms import TablaRegistroForm, SubRowForm, RowTablaRegistroForm

def inicio(request):
    return render(request, 'index.html')

def agregar_fila(request, tabla_id):
    if request.method == 'POST':
        print(request.POST)
        fecha = request.POST.get('fecha')
        detalle = request.POST.get('detalle')
        ventas = request.POST.get('ventas')
        costos_materiales = request.POST.get('costos_materiales')
        costos_personal = request.POST.get('costos_personal')
        gastos_generales = request.POST.get('gastos_generales')
        ingresos = request.POST.get('ingresos_caja')
        egresos = request.POST.get('egresos_caja')
        saldo = request.POST.get('saldo_caja')
        caja = SubRow.objects.create(ingresos=ingresos, egresos=egresos, saldo=saldo)
        caja.save()
        ingresos = request.POST.get('ingresos_banco')
        egresos = request.POST.get('egresos_banco')
        saldo = request.POST.get('saldo_banco')
        banco = SubRow.objects.create(ingresos=ingresos, egresos=egresos, saldo=saldo)
        banco.save()
        ingresos = request.POST.get('ingresos_iva')
        egresos = request.POST.get('egresos_iva')
        saldo = request.POST.get('saldo_iva')
        iva = SubRow.objects.create(ingresos=ingresos, egresos=egresos, saldo=saldo)
        iva.save()
        row = RowTablaRegistro.objects.create(fecha=fecha, detalle=detalle, ventas=ventas, costos_materiales=costos_materiales, costos_personal=costos_personal, gastos_generales=gastos_generales, caja=caja, banco=banco, iva=iva, TablaRegistro_id=tabla_id)
        row.save()
        return redirect('ver_tabla', id=tabla_id)
    else:
        return render(request,'agregar_fila.html',{
            'row_tabla_form': RowTablaRegistroForm(),
            'sub_row_form': SubRowForm(),
        })

def libros_de_registro(request):
    lista_tablas = list(TablaRegistro.objects.values())
    return render(request, 'libros_de_registro.html', {
        'tabla_registro_list': lista_tablas,
        'numero_tablas' : TablaRegistro.objects.count()
        })

def agregar_tabla(request):
    if request.method == 'POST':
        tabla_registro_form = TablaRegistroForm(request.POST)
        if tabla_registro_form.is_valid():
            tabla_registro_form.save()
            return redirect('libros_de_registro')
    else:
        return render(request, 'agregar_tabla.html', {'tabla_registro_form': TablaRegistroForm()})

def ver_tabla(request, id):
    tabla = TablaRegistro.objects.get(id=id)
    tabla_rows = RowTablaRegistro.objects.values()
    tabla_rows_subrows = SubRow.objects.values()

    libro = {
        'nombre': tabla.descripcion,
        'rows': []
    }

    for row in tabla_rows:
        for subrow in tabla_rows_subrows:
            if row['caja_id'] == subrow['id']:
                row['caja'] = subrow
            if row['banco_id'] == subrow['id']:
                row['banco'] = subrow
            if row['iva_id'] == subrow['id']:
                row['iva'] = subrow
        if row['TablaRegistro_id'] == tabla.id:
            libro['rows'].append(row)
        
    return render(request, 'ver_tabla.html', {
        'libro': libro,
        'tabla_id' : id
    })

def eliminar_tabla(request,id):
    try:
        tabla_a_eliminar = TablaRegistro.objects.get(id=id)
    except TablaRegistro.DoesNotExist:
        return render(request,'layouts/not_found.html')
    
    if request.method == 'POST':
        print(request.POST)
        if request.POST.get('confirmacion') == 'si':
            tabla_a_eliminar.delete()
        return redirect('libros_de_registro')
    return render(request,'eliminar_tabla.html',{
        'id' : id
    })