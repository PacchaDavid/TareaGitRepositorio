from django import forms

class CrearBrazoForm(forms.Form):
    nombre = forms.CharField(label='nombre del brazo')
    
class CrearUsuarioForm(forms.Form):
    username = forms.CharField(label='Ingrese nombre de Usuario',widget=forms.TextInput(attrs={'class' : 'input'}))
    password = forms.CharField(widget=forms.PasswordInput(attrs={'class':'input'}))