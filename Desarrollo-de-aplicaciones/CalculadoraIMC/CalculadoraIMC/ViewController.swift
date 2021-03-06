//
//  ViewController.swift
//  CalculadoraIMC
//
//  Created by Diego Montaño on 02/03/21.
//

import UIKit

class ViewController: UIViewController {

  
  @IBOutlet weak var peso: UITextField!
  @IBOutlet weak var errorPeso: UILabel!
  @IBOutlet weak var altura: UITextField!
  @IBOutlet weak var errorAltura: UILabel!
  @IBOutlet weak var labelIMC: UILabel!
  @IBOutlet weak var salidaIMC: UILabel!
  @IBOutlet weak var diagnostico: UILabel!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    errorPeso.text = "";
    errorAltura.text = "";
    labelIMC.text = "";
    salidaIMC.text = "";
    diagnostico.text = "";
  }
  
  override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
    //let sigVista = segue.destination as! ResultadosViewController;
  }
  
  @IBAction func calcularIMC(_ sender: UIButton) {
    let alturaValida = validarAltura(), pesoValido = validarPeso();
    if alturaValida && pesoValido {
      let pesoD: Double! = Double(peso.text!);
      let alturaD: Double! = Double(altura.text!);
      let imc = pesoD / alturaD / alturaD;
      labelIMC.text = "IMC";
      salidaIMC.text = String(imc);
      switch imc {
        case 0...18.5:
          diagnostico.text = "Bajo de peso";
        case 18.5...24.9:
          diagnostico.text = "Peso saludable";
        case 24.9...29.9:
          diagnostico.text = "Sobrepeso";
        case 29.9...34.9:
          diagnostico.text = "Obesidad de grado 1";
        case 34.9...40:
          diagnostico.text = "Obesidad de grado 2";
        default:
          diagnostico.text = "Obesidad morbida";
      }
    }
  }
  
  func validarAltura() -> Bool {
    var error = "";
    if let altura = self.altura.text {
      let alturaDouble = Double(altura);
      if let aux = alturaDouble {
        if aux > 2.72 {
          error = "La altura es demasiado grande";
        } else if(aux < 1) {
          error = "La altura es muy pequeña"
        } else {
          return true;
        }
      } else {
        error = "La altura debe ser un numero";
      }
    } else {
      error = "Tienes que especificar una altura"
    }
    errorAltura.text = error;
    return false;
  }
  
  func validarPeso() -> Bool {
    var error = "";
    if let peso = self.peso.text {
      let pesoDouble = Double(peso);
      if let aux = pesoDouble {
        if aux > 250 {
          error = "El peso es demasiado grande o consigue a un médico";
        } else if aux < 25 {
          error = "El peso es muy pequeño";
        } else {
          return true;
        }
      } else {
        error = "El peso debe ser un numero";
      }
    } else {
      error = "Tienes que especificar un peso"
    }
    errorPeso.text = error;
    return false;
  }
  
}

