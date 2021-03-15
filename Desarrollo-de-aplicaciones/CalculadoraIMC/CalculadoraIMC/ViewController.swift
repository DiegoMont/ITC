//
//  ViewController.swift
//  CalculadoraIMC
//
//  Created by Diego Montaño on 02/03/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

  
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
    
    peso.delegate = self;
    altura.delegate = self;
  }
  
  override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
    let sigVista = segue.destination as! ResultadosViewController;
    sigVista.altura = Double(altura.text!) ?? 1.0;
    sigVista.peso = Double(peso.text!) ?? 1.0;
  }
  
  @IBAction func terminarEditar(_ sender: UITextField) {
    sender.resignFirstResponder();
  }
  
  
  @IBAction func toque(_ sender: UITapGestureRecognizer) {
    peso.resignFirstResponder();
    altura.resignFirstResponder();
  }
  
  @IBAction func calcularIMC(_ sender: UIButton) {
    let alturaValida = validarAltura(), pesoValido = validarPeso();
    if alturaValida && pesoValido {
      
    }
  }
  
  func validarAltura() -> Bool {
    let mensajesError = ["La altura es demasiado grande", "La altura es muy pequeña", "La altura debe ser un numero", "Tienes que especificar una altura"];
    var error = "";
    if let altura = self.altura.text {
      let alturaDouble = Double(altura);
      if let aux = alturaDouble {
        if aux > 2.72 {
          error = mensajesError[0];
        } else if(aux < 1) {
          error = mensajesError[1];
        } else {
          return true;
        }
      } else {
        error = mensajesError[2];
      }
    } else {
      error = mensajesError[3];
    }
    //errorAltura.text = error;
    return false;
  }
  
  func validarPeso() -> Bool {
    let mensajesError = ["El peso es demasiado grande o consigue a un médico", "El peso es muy pequeño", "El peso debe ser un numero", "Tienes que especificar un peso"];
    var error = "";
    if let peso = self.peso.text {
      let pesoDouble = Double(peso);
      if let aux = pesoDouble {
        if aux > 250 {
          error = mensajesError[0];
        } else if aux < 25 {
          error = mensajesError[1];
        } else {
          return true;
        }
      } else {
        error = mensajesError[2];
      }
    } else {
      error = mensajesError[3];
    }
    //errorPeso.text = error;
    return false;
  }
  
}

