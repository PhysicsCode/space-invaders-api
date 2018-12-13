$(document).on('keypress', function(e) {

    if (e.key === 'ArrowDown') {
        setInstruction('down');
    } else if (e.key === 'ArrowUp') {
        setInstruction('up');
    } else if (e.key === 'ArrowLeft') {
        setInstruction('left');
    } else if (e.key === 'ArrowRight') {
        setInstruction('right');
    } else if (e.key === 's') {
        setInstruction('fire-down');
    } else if (e.key === 'w') {
        setInstruction('fire-up');
    } else if (e.key === 'a') {
        setInstruction('fire-left');
    } else if (e.key === 'd') {
        setInstruction('fire-right');
    } else if (e.key === ' ') {
        setInstruction(' ')
    }
});

var gp =  navigator.getGamepads()[0];

window.addEventListener("gamepadconnected", function(e) {
    gp = navigator.getGamepads()[e.gamepad.index];
    console.log("Gamepad connected at index %d: %s. %d buttons, %d axes.",
        gp.index, gp.id,
        gp.buttons.length, gp.axes.length);
});

setInterval(pollGamepads, 50);

function pollGamepads() {

    if (gp !== null) {

        if (gp.buttons[4].pressed) {
            if (gp.buttons[0].pressed) { //DOWN
                setInstruction('fire-down');;
            }
            if (gp.buttons[2].pressed) { //LEFT
                setInstruction('fire-left');
            }
            if (gp.buttons[1].pressed) { //RIGHT
                setInstruction('fire-right');
            }
            if (gp.buttons[3].pressed) { //UP
                setInstruction('fire-up');
            }
        } else {
            if (gp.buttons[0].pressed) { //DOWN
                setInstruction('down');;
            }
            if (gp.buttons[2].pressed) { //LEFT
                setInstruction('left');
            }
            if (gp.buttons[1].pressed) { //RIGHT
                setInstruction('right');
            }
            if (gp.buttons[3].pressed) { //UP
                setInstruction('up');
            }
            if (gp.buttons[5].pressed) {//NONE
                setInstruction(' ');
            }
        }
    }
};

