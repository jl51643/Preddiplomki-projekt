//
//  FirstViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import UIKit

class FirstViewController: UIViewController {
    
    // MARK: Outlets
    @IBOutlet weak var signUpButton: UIButton!
    @IBOutlet weak var loginButton: UIButton!
    
    // MARK: Lifecycle methods
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupUI()
    }
    
    // MARK: Actions
    @IBAction func signUpButtonTapped(_ sender: UIButton) {
        let signUpVC = SignUpViewController()
        navigationController?.pushViewController(signUpVC, animated: false)
    }
    
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        let loginVC = LoginViewController()
        navigationController?.pushViewController(loginVC, animated: true)
    }
    
    // MARK: Class methods
    func setupUI(){
        navigationController?.navigationItem.hidesBackButton = true
        navigationController?.navigationBar.isHidden = true
        self.view.backgroundColor = UIColor(patternImage: UIImage(named: "background.png")!).withAlphaComponent(CGFloat(0.7))
        UIButton.styleButton(button: signUpButton)
        UIButton.styleButton(button: loginButton)
    }
}

// MARK: Outlets
// MARK: Properties
// MARK: Lifecycle methods
// MARK: Actions
// MARK: Class methods
