//
//  ViewController.swift
//  HolaMundo101
//
//  Created by Diego Montaño on 26/02/21.
//

import UIKit

class ViewController: UIViewController {

  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
  }


  @IBOutlet weak var etiqueta: UILabel!
  @IBAction func saludar(_ sender: UIButton) {
    self.etiqueta.text = "Eureka"
  }
}

