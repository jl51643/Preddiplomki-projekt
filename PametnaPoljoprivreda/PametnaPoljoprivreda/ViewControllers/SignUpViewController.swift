//
//  SignUpViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import UIKit
import Alamofire

class SignUpViewController: UIViewController {
    
    // MARK: Outlets
    @IBOutlet weak var usernameTF: UITextField!
    @IBOutlet weak var mailTF: UITextField!
    @IBOutlet weak var passwordTF: UITextField!
    @IBOutlet weak var signUpButton: UIButton!
    
    // MARK: Properties
    private var userModel: UserModel?
    let baseUrlString = "http://ad8c14c1e2c4.ngrok.io"
    
    // MARK: Lifecycle methods
    override func viewDidLoad() {
        super.viewDidLoad()

        setupUI()
    }

    // MARK: Actions
    @IBAction func signUpButtonTapped(_ sender: UIButton) {
        //let loginService = LoginService()
        
        guard let username = usernameTF.text,
              let email = mailTF.text,
              let password = passwordTF.text  else {print("textfildovi prazni"); return}
        
        registerUserWith(username: username, email: email, password: password)
    
        
        
    }
    
    // MARK: Class methods

    func setupUI(){
        UIButton.styleButton(button: signUpButton)
        UITextField.styleTextField(textfield: usernameTF)
        UITextField.styleTextField(textfield: mailTF)
        UITextField.styleTextField(textfield: passwordTF)
        self.view.backgroundColor = UIColor(patternImage: UIImage(named: "background.png")!).withAlphaComponent(CGFloat(0.8))
    }
    
    //sa userId-om
    func navigateToHome(){
        navigationController?.pushViewController(CultureTableViewController(), animated: true)
    }
 
}

extension SignUpViewController {
    func registerUserWith(username: String, email: String, password: String) {

        let parameters: [String: String] = [
            "username": username,
            "email": email,
            "password": password
        ]

        let urlStr = baseUrlString + "/api/auth/register"

        AF.request(urlStr, method: .post, parameters: parameters, encoding: JSONEncoding.default)
            .validate()
            .responseDecodable(of: UserModel.self) { response in
                switch response.result {
                case .success(let user):
                    self.userModel = user
                    UserCredentials.shared.setToken(with: user.token)
                    self.navigateToHome()
                case .failure(let err):
                    print(err)
                }
            }




    }
}
