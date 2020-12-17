//
//  LoginViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import UIKit

class LoginViewController: UIViewController {
    
    // MARK: Outlets
    @IBOutlet weak var mailTF: UITextField!
    @IBOutlet weak var passwordTF: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    
    // MARK: Properties
    
    // MARK: Lifecycle methods
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupUI()

        // Do any additional setup after loading the view.
    }

    // MARK: Actions
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        
    }
    
    // MARK: Class methods
    func setupUI(){
        UIButton.styleButton(button: loginButton)
        UITextField.styleTextField(textfield: mailTF)
        UITextField.styleTextField(textfield: passwordTF)
        self.view.backgroundColor = UIColor(patternImage: UIImage(named: "background.png")!).withAlphaComponent(CGFloat(0.7))
    }
    
    func validateUserData() -> String? {
        if mailTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" ||
                passwordTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" {
                return "Some fields are empty"
        }
        // TODO: regex za mail i password jesu oke
        // idealno bi bilo ove stringove ili pod neki error label ispisat ili na alertController
        return nil
    }
    
    
}
