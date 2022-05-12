const express = require('express')

main()

function main() {
    const app = express()
    const port = process.env.PORT || 4321
    app.use(express.json())
    app.use(express.urlencoded({ extended: true }))
    app.use('/calculate', require('./controller/CalculatorController'))
    app.use((err, req, res, next) => {res.status(400).send({error: err.message})})
    app.listen(port, () => {
        console.log(`Server is up and listening on port ${port}. (http://localhost:${port})`)
    })
    app.get('/hello-world', function(req, res) {
        res.send('{"hello": "world"}');
    })
}
