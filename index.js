const score = require('./score');

const lines = [
    'XXXXXXXXXXXX',
    '9-9-9-9-9-9-9-9-9-9-',
    '5/5/5/5/5/5/5/5/5/5/5',
    'X7/9-X-88/-6XXX81'
];

lines.forEach(line => console.log('LINE: '+ line +' SCORE: '+score(line)));

/*
    EXAMPLE OUTPUT

    LINE: XXXXXXXXXXXX SCORE: 300
    LINE: 9-9-9-9-9-9-9-9-9-9- SCORE: 90
    LINE: 5/5/5/5/5/5/5/5/5/5/5 SCORE: 150
    LINE: X7/9-X-88/-6XXX81 SCORE: 167

 */