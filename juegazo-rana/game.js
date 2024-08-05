import { checkControls } from "./controls.js"
import { createAnimations } from "./animations.js"

const config = {
    type: Phaser.AUTO,
    width: 576,
    height: 384,
    backgroundColor: '#034',
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 300 },
            debug: false
        }
    },
    parent: 'game',
    scene: {
        preload,
        create,
        update
    }
}

new Phaser.Game(config)

function preload() {
    this.load.image(
        'background1',
        'assets/background/blue.png'
    )

    this.load.image(
        'floor1',
        'assets/terrain/cesped.png'
    )

    this.load.spritesheet(
        'rana-idle',
        'assets/main-characters/ninja-frog/idle.png',
        {
            frameWidth: 32,
            frameHeight: 32,
        }
    )

    this.load.spritesheet(
        'rana-run',
        'assets/main-characters/ninja-frog/run.png',
        {
            frameWidth: 32,
            frameHeight: 32,
        }
    )

    this.load.spritesheet(
        'rana-jump',
        'assets/main-characters/ninja-frog/jump.png',
        {
            frameWidth: 32,
            frameHeight: 32,
        }
    )

    this.load.spritesheet(
        'rana-dead',
        'assets/main-characters/ninja-frog/hit.png',
        {
            frameWidth: 32,
            frameHeight: 32,
        }
    )

}

function create() {

    //Dibujar Fondo del juego
    var sceneX = 0
    var sceneY = 32
    var j, i;
    for(j = 0; j < 6; j++) {
        sceneX = 32
        for(i = 0; i < 18; i++) {
            this.add.image(sceneX,sceneY,'background1')
            .setOrigin(0.5,0.5)
            sceneX += 64;
        }
        sceneY += 64         
    }

    //crear el objeto floor o suelo
    this.floor = this.physics.add.staticGroup()
    sceneX = 24

    //dibujar Suelo
    for(i = 0; i < 20; i++) {
        if(i != 4 && i != 7 && i != 10) {
            this.floor.create(sceneX,360,'floor1')
            .setOrigin(0.5,0.5)
            .refreshBody()
        }       
        sceneX += 48 
    }
    
    //crear personaje jugable rana
    this.rana = this.physics.add.sprite(32,256,'rana-idle')
                    .setOrigin(0.5,0.5)
                    .setCollideWorldBounds(true)
                    .setGravityY(100)

    this.physics.add.collider(this.rana,this.floor)
    this.physics.world.setBounds(0,0,2000,config.height)

    this.cursorKeys = this.input.keyboard.createCursorKeys()

    this.cameras.main.setBounds(0,0,2000,config.height)
    this.cameras.main.startFollow(this.rana)

    //Crear Animaciones
    createAnimations(this)
    
}

function update() {
    const { rana, scene } = this
    
    if(rana.isDead) return

    const isRanaTouchingDown = rana.body.touching.down
    const isRanaTouchingLeft = rana.body.touching.left
    const isRanaTouchingRight = rana.body.touching.right

    checkControls(this)
    if(rana.y >= config.height-16) {
        rana.isDead = true
        rana.setCollideWorldBounds(false)
        rana.anims.play('dead-rana',true)

        setTimeout(()=>{
            rana.setVelocityY(-100)
            rana.setVelocityX(-50)
        },100)

        setTimeout(()=>{
            scene.restart()
        },1500)
    }
}




