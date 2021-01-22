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
    @IBOutlet weak var dissmissButton: UIButton!
    @IBOutlet weak var addButton: UIButton!
    
    weak var delegate: AddDeviceDelagate?
    
    private var viewModel: CulturesViewModel
    
    init(viewModel: CulturesViewModel) {
        self.viewModel = viewModel
        super.init(nibName: "AddDeviceViewController", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
    }


    @IBAction func dissmissButtonTapped(_ sender: UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func addButtonTapped(_ sender: UIButton) {
        
        guard let id = idTF.text else {return}
        
        let servise = MeasurmentsService()
        guard let cultureId = viewModel.selectedCulture?.cultureId else {return}
        DispatchQueue.main.async {
            servise.addDeviceToCulture(cultureID: cultureId, id: id) { result in
                switch result {
                case.success(let cultures):
                    self.viewModel.cultures = cultures
                   
                    self.viewModel.selectedCulture = cultures[self.viewModel.selectedIndex!]
                    self.delegate?.didAddDevice()
                case .failure(let error):
                    debugPrint(error)
                }
            }
        }
        self.dismiss(animated: true, completion: nil)
    }
    
    func setupUI(){
        UITextField.styleTextField(textfield: idTF)
        UIButton.styleButton(button: dissmissButton)
        UIButton.styleButton(button: addButton)
    }
}


