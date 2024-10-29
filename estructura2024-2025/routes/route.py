from flask import Blueprint, abort, request, render_template, redirect
import requests

router = Blueprint('router',__name__)

@router.route('/')
def home():
    r = requests.get("http://localhost:8099/api/persona/all")
    print("hola")
    print(type(r.json()))
    print(r.json())
    return render_template('index.html',list=r.json()['info'])

@router.route('/persona/<int:id>')
def persona(id):
    r = requests.get(f"http://localhost:8099/api/persona/get/{id}")
    print("hola")
    print(type(r.json()))
    print(r.json())
    return render_template('persona.html',item=r.json()['info'])

@router.route('/persona/lista')
def list_persona():
    r = requests.get(f'http://localhost:8099/api/persona/all')
    data = r.json()
    return render_template('fragmento/persona/lista.html',list=data['info'])


@router.route('/persona/save')
def save_persona():
    return render_template('fragmento/persona/formPersona.html')
    

@router.route('/persona/send',methods=['POST'])
def send_persona():
    data = request.form.to_dict()
    print('DATA MANUAL:\n')
    response = requests.post('http://localhost:8099/api/persona/save',json=data)
    if response.status_code == 200:
        return redirect('/persona/lista')
    else:
        return '<h1>algo ha salido mal!</h1>'

""" Anotaciones 
return redirect  ('/persona/lista')
<input type="hidden" name="id">
"""
    