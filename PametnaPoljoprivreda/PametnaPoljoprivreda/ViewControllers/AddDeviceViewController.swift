//
//  AddDeviceViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 13.01.2021..
//

import UIKit

protocol AddDeviceDelagate: class {
    func didAddDevice()
}

class AddDeviceViewController: UIViewController {
    @IBOutlet weak var idTF: UITextField!
    @IBOutlet weak var devIdTF: UITextField!
    @IBOutlet weak var dissmissButton: UIButton!
    @IBOutlet weak var addButton: UIButton!
    
    weak var delegate: AddDeviceDelagate?
    
    var cultureId: Int?
    
    convenience init(cultureId: Int){
        self.init()
        self.cultureId = cultureId
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        setupUI()
        // Do any additional setup after loading the view.
    }


    @IBAction func dissmissButtonTapped(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func addButtonTapped(_ sender: UIButton) {
        
        guard let id = idTF.text,
              let devId = devIdTF.text else {return}
        
        let servise = MeasurementsService()
        guard let cultureId = cultureId else {return}
        DispatchQueue.main.async {
            servise.addDeviceToCulture(cultureID: cultureId, id: id, devID: devId)
            self.delegate?.didAddDevice()
        }
        self.dismiss(animated: true, completion: nil)
    }
    
    func setupUI(){
        UITextField.styleTextField(textfield: idTF)
        UITextField.styleTextField(textfield: devIdTF)
        UIButton.styleButton(button: dissmissButton)
        UIButton.styleButton(button: addButton)
    }
}


