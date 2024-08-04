Sistema Futbol
-

El diagrama de clases se encuentra abajo de las capturas c:

Actualización: 
- Se actualizaron las vistas
- Los modelos han sido separados en varios archivos dentro del directorio sistema_futbol_app/models/
- Se implementaron las herencias para reutilización de código

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Instrucciones para instalación
- Clonar o descargar el repositorio
- Abrir la carpeta sistema_futbol con su IDE de preferencia
- Iniciar un nuevo entorno virtual en el directorio raíz del proyecto (automático en algunos Entornos de Desarrollo)
- Ejecutar en la consola el siguiente comando 'pip install -r requirements.txt' (debe tener instalado el gestor de paquetes y dependencias pip)
- Ejecutar el comando: 'python manage.py runserver'
- Abrir el navegador y escribir la ruta 'localhost:8000'
- Navegar por la aplicación

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Navegación a través de la app

Vista principal '/'
![image](https://github.com/user-attachments/assets/413b01ad-4514-431a-a02b-6fe88cd0c4d0)

Vista Campeonatos 'campeonatos/'
![image](https://github.com/user-attachments/assets/2d33de3d-5a8a-444d-a454-e95bc5222d98)
![image](https://github.com/user-attachments/assets/d061b5eb-eed8-4d25-a8c4-8ee4ba804775)

Vista Equipos Participantes 'equipos/<int:id>/' (depende del campeonato)
![image](https://github.com/user-attachments/assets/f476408e-b49a-4297-9f79-8b48ab2a9c71)

Vista ver equipo 'ver_equipo/<int:id>/' (depende del equipo seleccionado)
![image](https://github.com/user-attachments/assets/9263c3bb-d1f3-4524-9f40-b8e6ffdf0572)

Vista tabla de posiciones 'ver_tabla/<int:campeonato_id>/' (depende del campeonato)
![image](https://github.com/user-attachments/assets/1ed70218-a1a0-4192-a08e-6cb8328d33f0)

Vista partidos 'partidos/<int:tabla_id>/' (depende de la tabla de posiciones)
![image](https://github.com/user-attachments/assets/bc625cc3-1dcb-46c2-ad6e-7d6da20419bc)

Vista ver partido 'ver_partido/<int:id>/' 
![image](https://github.com/user-attachments/assets/1beddfce-6e56-4be4-9332-fc8a2cdfd626)

Vista estadistica partido 'ver_estadistica_partido/<int:id>/'
![image](https://github.com/user-attachments/assets/5882425a-5a7f-4530-b32b-7e0b147a8162)


Diagrama de clases

![sistemaFutbol](https://github.com/user-attachments/assets/065bf9a5-55bd-47db-b655-8d4a4dcf8fcd)

