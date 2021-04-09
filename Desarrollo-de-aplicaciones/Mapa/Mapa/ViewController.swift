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
    locationManager.delegate = self;
    locationManager.desiredAccuracy = kCLLocationAccuracyBestForNavigation;
    locationManager.requestWhenInUseAuthorization();
    let punto = CLLocationCoordinate2DMake(19.295427868882417, -99.13266686009915);
    let ccm = MKPointAnnotation();
    ccm.coordinate = punto;
    ccm.title = "Montacueva";
    ccm.subtitle = "Saquen las chelas";
    mapa.region = MKCoordinateRegion(center: punto, latitudinalMeters: 2000, longitudinalMeters: 2000);
    mapa.addAnnotation(ccm);
    mapa.showsCompass = true;
    //mapa.mapType = MKMapType.hybrid;
    mapa.showsTraffic = true;
  }
  
  func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
    if(status == .authorizedWhenInUse){
      locationManager.startUpdatingLocation();
      mapa.showsUserLocation = true;
    } else {
      locationManager.stopUpdatingLocation();
      mapa.showsUserLocation = false;
    }
  }
  //19.28398351707071, -99.13628023112184
}

