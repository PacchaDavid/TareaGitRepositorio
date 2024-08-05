export const createAnimations = (game) => {
    game.anims.create({
        key: 'idle-rana',
        frames: game.anims.generateFrameNumbers(
            'rana-idle',
            {
                start: 0,
                end: 10,
            }
        ),
        frameRate: 15,
        repeat: -1
    }) 
    
    game.anims.create({
        key: 'run-rana',
        frames: game.anims.generateFrameNumbers(
            'rana-run',
            {
                start: 0,
                end: 10,
            }
        ),
        repeat: -1
    })  

    game.anims.create({
        key: 'jump-rana',
        frames: [{key: 'rana-jump', frame: 0}]
    })
    
    game.anims.create({
        key: 'dead-rana',
        frames: game.anims.generateFrameNumbers(
            'rana-dead',
            {
                start: 0,
                end: 6,
            }
        ),
        frameRate: 12,
        repeat:-1
    })
}