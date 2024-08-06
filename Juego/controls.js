export function checkControls({ rana, cursorKeys }) {
    if(rana.isDead) return;
    const isLeftKeyPressed = cursorKeys.left.isDown
    const isRightKeyPressed = cursorKeys.right.isDown
    const isUpKeyPressed = cursorKeys.up.isDown

    const isRanaTouchingDown = rana.body.touching.down
    const isRanaTouchingLeft = rana.body.touching.left
    const isRanaTouchingRight = rana.body.touching.right

    if(isLeftKeyPressed) {
        rana.flipX = true
        isRanaTouchingDown && rana.anims.play('run-rana',true)
        rana.setVelocityX(-150)
    } else if(isRightKeyPressed) {
        rana.flipX = false
         isRanaTouchingDown && rana.anims.play('run-rana',true)
        rana.setVelocityX(150)
    } else {
        rana.anims.play('idle-rana',true)
        rana.setVelocityX(0)
    }
    if(isUpKeyPressed && isRanaTouchingDown) {
        rana.anims.play('jump-rana',true)
        rana.setVelocityY(-100)
    }   
}