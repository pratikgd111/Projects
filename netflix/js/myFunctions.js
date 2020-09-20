function add() {
    const number1 = parseInt(document.getElementById('number1').value)
    const number2 = parseInt(document.getElementById('number2').value)
    const result = number1 + number2
    document.getElementById('result').innerHTML = `Result = ${result}`
  }
  