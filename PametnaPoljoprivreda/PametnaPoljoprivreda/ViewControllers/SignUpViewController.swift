//
//  SignUpViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import UIKit

class SignUpViewController: UIViewController {
    
    // MARK: Outlets
    @IBOutlet weak var nameTF: UITextField!
    @IBOutlet weak var surnameTF: UITextField!
    @IBOutlet weak var mailTF: UITextField!
    @IBOutlet weak var nicknameTF: UITextField!
    @IBOutlet weak var passwordTF: UITextField!
    @IBOutlet weak var signUpButton: UIButton!
    
    // MARK: Properties
    
    // MARK: Lifecycle methods
    override func viewDidLoad() {
        super.viewDidLoad()

        setupUI()
    }

    // MARK: Actions
    @IBAction func signUpButtonTapped(_ sender: UIButton) {
    }
    
    // MARK: Class methods

    func setupUI(){
        UIButton.styleButton(button: signUpButton)
        UITextField.styleTextField(textfield: nameTF)
        UITextField.styleTextField(textfield: surnameTF)
        UITextField.styleTextField(textfield: mailTF)
        UITextField.styleTextField(textfield: nicknameTF)
        UITextField.styleTextField(textfield: passwordTF)
    }
    
    func validateUserData() -> String? {
        if nameTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" ||
                surnameTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" ||
                mailTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" ||
                nicknameTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" ||
                passwordTF.text?.trimmingCharacters(in: .whitespacesAndNewlines) == "" {
                return "Some fields are empty"
        }
        // TODO: regex za mail i password jesu oke
        // idealno bi bilo ove stringove ili pod neki error label ispisat ili na uialertcontroller
        
        return nil
    }

}
