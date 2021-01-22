//
//  AddCultureViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 12.01.2021..
//

import UIKit

protocol AddCultureDelegate: class {
    func didAddCulture()
}

class AddCultureViewController: UIViewController {
    @IBOutlet weak var cultureIdTF: UITextField!
    @IBOutlet weak var titleTF: UITextField!
    @IBOutlet weak var deviceIdTF: UITextField!
    @IBOutlet weak var deviceDevIdTF: UITextField!
    @IBOutlet weak var descriptionTF: UITextField!
    @IBOutlet weak var addButton: UIButton!
    @IBOutlet weak var dissmisButton: UIButton!
    
    weak var resultDelagate: AddCultureDelegate?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUi()
      
    }

    @IBAction func dissmisButtonTapped(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func addButtonTapped(_ sender: UIButton) {
        
        guard let cultureID = cultureIdTF.text,
              let title = titleTF.text,
              let deviceID = deviceIdTF.text,
              let deviceDevID = deviceDevIdTF.text,
              let desc = descriptionTF.text else {return}
        
        
        let service = MeasurmentsService()
        
        DispatchQueue.main.async {
            service.addCulture(cultureID: cultureID, title: title, deviceID: deviceID, deviceDevID: deviceDevID, description: desc){
                result in
                switch result {
                case.success(let viewModel):
                    self.resultDelagate?.didAddCulture()
                case .failure(let error):
                    debugPrint(error)
                }

            }
            self.resultDelagate?.didAddCulture()
        }
        
        
        
        self.dismiss(animated: true, completion: nil)
    }
    

    func setupUi() {
        UITextField.styleTextField(textfield: cultureIdTF)
        UITextField.styleTextField(textfield: titleTF)
        UITextField.styleTextField(textfield: descriptionTF)
        UITextField.styleTextField(textfield: deviceIdTF)
        UITextField.styleTextField(textfield: deviceDevIdTF)
        UIButton.styleButton(button: addButton)
        UIButton.styleButton(button: dissmisButton )
    }
}
