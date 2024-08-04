// Global Phaser object
const config = {
    type: Phaser.AUTO, //Primero intentará Webgl, luego Canvas ...
    width: 256,
    height: 240,
    backgroundColor: '#049cd8',
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 300 },
            debug: false
        }
    },
    parent: 'game',
    scene: {
        preload,  //precargar recursos
        create, //se ejecuta cuando el juego comieza
        update,  //se ejecuta en cada frame
    }
}

new Phaser.Game(config)
//this --> game --> juego que estamos construyendo 

function preload() { //Se ejecuta primero
    this.load.image(
        'box1',
        'Free/items/Boxes/Box1/Idle.png'
    )

    this.load.image(
        'floor1',
        'Free/Terrain/stone-block.png'
    )
    
    this.load.spritesheet(
        'rana', // <-- identificador único 
        'Free/MainCharacters/NinjaFrog/Idle.png',
        { frameWidth: 32 } 
    )

    this.load.spritesheet(
        'rana-run',
        'Free/MainCharacters/NinjaFrog/Run.png',
        { frameWidth: 32 }
    )

    this.load.spritesheet(
        'rana-jump',
        'Free/MainCharacters/NinjaFrog/Jump.png',
        { frameWidth: 32, frameHeight: 32 }
    )

    this.load.spritesheet(
        'rana-fall',
        'Free/MainCharacters/NinjaFrog/Fall.png',
        { frameWidth: 32, frameHeight: 32 }
    )

    this.load.spritesheet(
        'rana-death',
        'Free/MainCharacters/NinjaFrog/Hit.png',
        { frameWidth: 32, frameHeight: 32 }
    )

}

function create() { //Se ejecuta despues
    //se debe especificar la posición en píxeles
    this.add.image(0,0,'box1')// posición x , y , 'imagen'
        .setOrigin(0,0) 
        .setScale(1.9)
    
    //donde empieza 0,0  y hasta donde llega
    this.floor = this.physics.add.staticGroup()
    this.floor
      .create(50,202,'floor1')
      .setOrigin(0.5,0.5)
      .refreshBody()

    this.floor
      .create(170,202,'floor1')
      .setOrigin(0.5,0.5)
      .refreshBody()
      
    this.floor.create(110,230,'floor1')

    this.rana = this.physics.add.sprite(60,50,'rana')
                .setScale(1.1)
                .setOrigin(0,1)
                .setGravityY(300)
                .setCollideWorldBounds(true)            
    
    this.keys = this.input.keyboard.createCursorKeys()
    this.physics.add.collider(this.rana,this.floor)
    this.physics.world.setBounds(0,0,2000,config.height)
    this.cameras.main.setBounds(0,0,2000,config.height)
    this.cameras.main.startFollow(this.rana)

    this.anims.create({
        key: 'run-rana',
        frames: this.anims.generateFrameNumbers(
            'rana-run',
            {start: 0, end: 11}
        ),
        repeat: -1
    })

    this.anims.create({
        key: 'idle-rana',
        frames: this.anims.generateFrameNumbers(
            'rana',
            { start: 0, end: 10 }
        ),
        repeat: -1
    })

    this.anims.create({
        key: 'jump-rana',
        frames: [
            { key: 'rana-jump', frame: 0}
        ],
        repeat: 1
    })

    this.anims.create({
        key: 'fall-rana',
        frames: [
            { key: 'rana-fall', frame: 0}
        ],
        repeat: 1
    })

    this.anims.create({
        key: 'death-rana',
        frames: this.anims.generateFrameNumbers(
            'rana-death',
            { start: 6, end: 2}
        ),
        repeat: -1
    })
}



function update() { //constantemente
    if(this.rana.isDead) return

   if(this.keys.left.isDown) {
    this.rana.x -= 2
    this.rana.anims.play('run-rana', true)
    this.rana.flipX = true
   } else if (this.keys.right.isDown) {
    this.rana.x += 2
    this.rana.anims.play('run-rana', true)
    this.rana.flipX = false
   } else { 
    this.rana.anims.play('idle-rana', true)
   }
   if (this.keys.up.isDown && this.rana.body.touching.down) {
    this.rana.setVelocityY(-300)
    if(this.rana.body.touching.down==false) {
        this.rana.anims.play('jump-rana', true)
    }
   }

   if(this.rana.y >= config.height) {
    this.rana.isDead = true
    this.rana.anims.play('death-rana')
    this.rana.setCollideWorldBounds(false)

    setTimeout(() => {
        this.rana.setVelocityY(-300)
        this.rana.setVelocityX(-100)
    },100)

    setTimeout(() => {
        this.scene.restart()
    },2000)
   }
}