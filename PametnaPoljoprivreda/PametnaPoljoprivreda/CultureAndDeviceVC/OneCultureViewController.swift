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
    
    private var viewModel: CulturesViewModel
    
    init(viewModel: CulturesViewModel) {
        self.viewModel = viewModel
        super.init(nibName: "OneCultureViewController", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        titleLabel.text = viewModel.selectedCulture?.title
        descLabel.text = viewModel.selectedCulture?.description
        UILabel.styleLabel(label: titleLabel)
        UILabel.styleLabel(label: descLabel)
        UIButton.styleButton(button: devicesButton)
        UIButton.styleButton(button: measurmentsButton)
    }


    @IBAction func devicesButtonTapped(_ sender: UIButton) {
        
//        guard let devices = model?.devices,
//              let cultureID = model?.cultureId else {return}
        
        let deviceVC = DevicesViewController(viewModel: viewModel)
        
        navigationController?.pushViewController(deviceVC, animated: true)
    }
    
    @IBAction func measurmentsButtonTapped(_ sender: UIButton) {
        let tabBarVC = TabBarViewController()
        navigationController?.pushViewController(tabBarVC, animated: true)
    }
    
}
