$(document).on('keypress', function(e) {

    if (e.key == 'ArrowDown') {
        setInstruction('down');
    } else if (e.key == 'ArrowUp') {
        setInstruction('up');
    } else if (e.key == 'ArrowLeft') {
        setInstruction('left');
    } else if (e.key == 'ArrowRight') {
        setInstruction('right');
    } else if (e.key == 's') {
        setInstruction('fire-down');
    } else if (e.key == 'w') {
        setInstruction('fire-up');
    } else if (e.key == 'a') {
        setInstruction('fire-left');
    } else if (e.key == 'd') {
        setInstruction('fire-right');
    } else if (e.key == ' ') {
        setInstruction(' ')
    }
});