from django.db import models

class SubRow(models.Model):
    ingresos = models.DecimalField(max_digits=10, decimal_places=2)
    egresos = models.DecimalField(max_digits=10, decimal_places=2)
    saldo = models.DecimalField(max_digits=10, decimal_places=2)

class RowTablaRegistro(models.Model):
    fecha = models.DateField()
    detalle = models.CharField(max_length=100)
    caja = models.ForeignKey(SubRow, related_name='caja', on_delete=models.CASCADE)
    banco = models.ForeignKey(SubRow, related_name='banco', on_delete=models.CASCADE)
    ventas = models.DecimalField(max_digits=10, decimal_places=2)
    costos_materiales = models.DecimalField(max_digits=10, decimal_places=2)
    costos_personal = models.DecimalField(max_digits=10, decimal_places=2)
    gastos_generales = models.DecimalField(max_digits=10, decimal_places=2)
    iva = models.ForeignKey(SubRow, related_name='iva', on_delete=models.CASCADE)
    TablaRegistro = models.ForeignKey('TablaRegistro', on_delete=models.CASCADE)

class TablaRegistro(models.Model):
    descripcion = models.CharField(max_length=100)

    def __str__(self):
        return self.descripcion