const readline = require('readline');
const math = require('./mate');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let operations;

let input = "";
let onMenu = true;
let operation;
let nums = [];
rl.on('line', function(line){
    const n = Number(line);
    if(isNaN(n)) {
        console.log('Invalid input');
        return;
    }
    if (onMenu) {
        operation = operations[n % operations.length];
        onMenu = false;
    } else
        nums.push(n);
    operation();
});

const main = function() {
    operations = [Add, Substract, Multiply, Divide, Exit];
    printMenu(); 
}

const printMenu = function(){
    for (let i = 0; i < operations.length; i++)
        console.log(`${i}) ${operations[i].name}`);
}

const haveEnoughInput = function() {
    if(nums.length == 2)
        return true;
    console.log('Enter a number: ');
    return false;
}

const Add = function() {
    if(!haveEnoughInput())
        return;
    const a = nums.pop();
    const b = nums.pop();
    console.log(math.add(a, b));
    onMenu = true;
    printMenu();
}

const Substract = function() {
    if(!haveEnoughInput())
        return;
    const a = nums.pop();
    const b = nums.pop();
    console.log(math.substract(a, b));
    onMenu = true;
    printMenu();
}

const Multiply = function() {
    if(!haveEnoughInput())
        return;
    const a = nums.pop();
    const b = nums.pop();
    console.log(math.multiply(a, b));
    onMenu = true;
    printMenu();
}

const Divide = function() {
    if(!haveEnoughInput())
        return;
    const a = nums.pop();
    const b = nums.pop();
    console.log(math.divide(a, b));
    onMenu = true;
    printMenu();
}

const Exit = function() {
    process.exit(0);
}

main();
