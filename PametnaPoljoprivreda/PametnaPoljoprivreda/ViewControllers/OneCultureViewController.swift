//
//  OneCultureViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 13.01.2021..
//

import UIKit

class OneCultureViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var descLabel: UILabel!
    @IBOutlet weak var devicesButton: UIButton!
    @IBOutlet weak var measurmentsButton: UIButton!
    
    var model: CulturesModel?
    
    convenience init(model: CulturesModel) {
        self.init()
        self.model = model
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        titleLabel.text = model?.title
        descLabel.text = model?.description
        UILabel.styleLabel(label: titleLabel)
        UILabel.styleLabel(label: descLabel)
        UIButton.styleButton(button: devicesButton)
        UIButton.styleButton(button: measurmentsButton)

        
    }


    @IBAction func devicesButtonTapped(_ sender: UIButton) {
        
        guard let devices = model?.devices,
              let cultureID = model?.cultureId else {return}
        
        let deviceVC = DevicesViewController(model: devices, cultureID: cultureID)
        
        navigationController?.pushViewController(deviceVC, animated: true)
    }
    
    @IBAction func measurmentsButtonTapped(_ sender: UIButton) {
        let tabBarVC = TabBarViewController()
        navigationController?.pushViewController(tabBarVC, animated: true)
    }
    
}
