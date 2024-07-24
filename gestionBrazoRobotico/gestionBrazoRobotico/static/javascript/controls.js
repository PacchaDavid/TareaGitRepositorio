var ipDirection;

var baseInput = document.getElementById('Base');
var hombroInput = document.getElementById('Hombro');
var codoInput = document.getElementById('Codo');
var pinzaInput = document.getElementById('Pinza');
var flashInput = document.getElementById('Flash');

var baseInputValue = document.getElementById('angle1');
var hombroInputValue = document.getElementById('angle2');
var codoInputValue = document.getElementById('angle3');
var pinzaInputValue = document.getElementById('angle4');
var flashInputValue = document.getElementById('flashValue');

var buttonConection = document.getElementById('alternarConexion');
var inputIP = document.getElementById('direccionIP');

var angle1 = 90;
var angle2 = 90;
var angle3 = 90;
var angle4 = 90;

var interval = null;


function updateValues(input, inputValue, angle, direction) {
        if(!interval && angle <= 180 && angle >= 0 ) {
            interval = setInterval(function() {
                angle += direction;
                input.value = angle;
                inputValue.textContent = angle;
            }, 30)
        }
}

document.addEventListener('keydown', function(event) {
    switch(event.key) {
        case 'j':
            updateValues(baseInput,baseInputValue,angle1,-1);
            break;
        case 'l':
            updateValues(baseInput,baseInputValue,angle1,+1);
            break;
        case 'i':
        //To Do: any
            break;
        case 'k':
        //To Do: any
            break;
        case 'w':
        //To Do: any
        break;
        case 's':
            //To Do: any
            break;
        case 'a':
            //To Do: any
            break;
        case 'd':
                //To Do: any
                break;
        default:
            console.log(event.key)
            break;
    }

});

document.addEventListener('keyup', function() {
    clearInterval(interval);
    interval = null;
})

buttonConection.addEventListener('click', () => {
    if(buttonConection.textContent === 'Conectar') {
        ipDirection = inputIP.value;
        if(validarIPv4(ipDirection)) {
            initWebSocket();
        } else {
            inputIP.value = 'Dirección IP inválida!!';
            return;
        }
        inputIP.style.display = 'none';
        buttonConection.style.display = 'none';
    }
});

function initCameraWebSocket() 
{
    websocketCamera = new WebSocket('ws://' + ipDirection + '/Camera');
    websocketCamera.binaryType = 'blob';
    websocketCamera.onopen    = function(event){};
    websocketCamera.onclose   = function(event){setTimeout(initCameraWebSocket, 2000);};
    websocketCamera.onmessage = function(event)
    {
    var imageId = document.getElementById("cameraImage");
    imageId.src = URL.createObjectURL(event.data);
    };
}

function initServoInputWebSocket() 
{
    websocketServoInput = new WebSocket('ws://' + ipDirection + '/ServoInput');
    websocketServoInput.onopen    = function(event)
    {
        sendButtonInput("Base", baseInput.value);
        sendButtonInput("Hombro", hombroInput.value);
        sendButtonInput("Codo", codoInput.value);   
        sendButtonInput("Pinza", pinzaInput.value);        
    };
    websocketServoInput.onclose   = function(event){setTimeout(initServoInputWebSocket, 2000);};
    websocketServoInput.onmessage = function(event){};        
}

function initWebSocket() 
{
    initCameraWebSocket ();
    initServoInputWebSocket();
}


function sendButtonInput(key, value) 
{
    var data = key + "," + value;
    websocketServoInput.send(data);
}
    


baseInput.addEventListener('input', () => {
    document.getElementById('angle1').textContent = baseInput.value
});

hombroInput.addEventListener('input', () => {
    document.getElementById('angle2').textContent = hombroInput.value
});

codoInput.addEventListener('input', () => {
    document.getElementById('angle3').textContent = codoInput.value
});

pinzaInput.addEventListener('input', () => {
    document.getElementById('angle4').textContent = pinzaInput.value
});

function validarIPv4(ip) {
    var ipv4Regex = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var match = ipv4Regex.exec(ip);

    if (!match) {
        return false; 
    }
    
    for (var i = 1; i <= 4; i++) {
        if (parseInt(match[i], 10) > 255) {
            return false; 
        }
    }

    return true; 
}



