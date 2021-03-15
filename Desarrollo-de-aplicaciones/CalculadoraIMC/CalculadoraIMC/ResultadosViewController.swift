//
//  ResultadosViewController.swift
//  CalculadoraIMC
//
//  Created by Diego Montaño on 05/03/21.
//

import UIKit

class ResultadosViewController: UIViewController {

  @IBOutlet weak var IMCNumerico: UILabel!
  @IBOutlet weak var IMCTextual: UILabel!
  @IBOutlet weak var imagen: UIImageView!
  
  var peso = 1.0, altura = 1.0;
  
  override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    let imc = peso / altura / altura;
    IMCNumerico.text = String(format: "%.02f", imc);
    switch imc {
      case 0...18.5:
        IMCTextual.text = "Bajo de peso";
        imagen.image = UIImage(named: "Desnutricion");
        IMCTextual.textColor = UIColor(red: 146/255, green: 188/255, blue: 212/255, alpha: 1);
      case 18.5...24.9:
        IMCTextual.text = "Peso saludable";
        imagen.image = UIImage(named: "Normal");
        IMCTextual.textColor = UIColor(red: 53/255, green: 206/255, blue: 150/255, alpha: 1);
      case 24.9...29.9:
        IMCTextual.text = "Sobrepeso";
        imagen.image = UIImage(named: "Obesidad");
        IMCTextual.textColor = UIColor(red: 252/255, green: 230/255, blue: 0, alpha: 1);
      case 29.9...34.9:
        IMCTextual.text = "Obesidad de grado 1";
        imagen.image = UIImage(named: "Obesidad");
        IMCTextual.textColor = UIColor(red: 252/255, green: 230/255, blue: 0, alpha: 1);
      case 34.9...40:
        IMCTextual.text = "Obesidad de grado 2";
        imagen.image = UIImage(named: "Obesidad");
        IMCTextual.textColor = UIColor(red: 252/255, green: 230/255, blue: 0, alpha: 1);
      default:
        IMCTextual.text = "Obesidad morbida";
        imagen.image = UIImage(named: "Obesidad");
        IMCTextual.textColor = UIColor(red: 252/255, green: 230/255, blue: 0, alpha: 1);
    }
  }

}
