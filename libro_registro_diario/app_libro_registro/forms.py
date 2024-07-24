from django import forms
from .models import TablaRegistro, SubRow, RowTablaRegistro

class TablaRegistroForm(forms.ModelForm):
    class Meta:
        model = TablaRegistro
        fields = '__all__'

class SubRowForm(forms.ModelForm):
    class Meta:
        model = SubRow
        fields = '__all__'

class RowTablaRegistroForm(forms.ModelForm):
    class Meta:
        model = RowTablaRegistro
        fields = '__all__'
