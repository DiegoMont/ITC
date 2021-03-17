//
//  ViewController.swift
//  DatosTabulares
//
//  Created by Diego Montaño on 17/03/21.
//

import UIKit

class ViewController: UIViewController {
  
  var profesorEnviado = "";
  
  @IBOutlet weak var profesorDesplegado: UILabel!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    profesorDesplegado.text = profesorEnviado;
  }


}

