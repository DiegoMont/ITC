//
//  ViewController.swift
//  Mapa
//
//  Created by Diego Montaño on 06/04/21.
//

import UIKit
import MapKit
import CoreLocation

class ViewController: UIViewController, CLLocationManagerDelegate {
  
  let locationManager = CLLocationManager();
  
  @IBOutlet weak var mapa: MKMapView!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view.
    let punto = CLLocationCoordinate2DMake(19.284094912672675, -99.13629095976724);
    let ccm = MKPointAnnotation();
    ccm.coordinate = punto;
    ccm.title = "Montahaus";
    ccm.subtitle = "Saquen las chelas";
    mapa.region = MKCoordinateRegion(center: punto, latitudinalMeters: 1000, longitudinalMeters: 1000);
    mapa.addAnnotation(ccm);
  }


}

