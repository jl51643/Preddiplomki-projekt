//
//  LoginViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import UIKit
import Alamofire

class LoginViewController: UIViewController {
    
    // MARK: Outlets
    @IBOutlet weak var usernameTF: UITextField!
    @IBOutlet weak var passwordTF: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    
    // MARK: Properties
    private var userModel: UserModel?
    let baseUrlString = Constants.baseUrl
    
    // MARK: Lifecycle methods
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupUI()

    }

    // MARK: Actions
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        
        guard let username = usernameTF.text,
              let password = passwordTF.text  else {print("textfildovi prazni"); return}
        
        loginUserWith(username: username, password: password)
        
    }
    
    // MARK: Class methods
    func setupUI(){
        UIButton.styleButton(button: loginButton)
        UITextField.styleTextField(textfield: passwordTF)
        UITextField.styleTextField(textfield: usernameTF)
        self.view.backgroundColor = UIColor(patternImage: UIImage(named: "background.png")!).withAlphaComponent(CGFloat(0.7))
    }
    
 
    //sa userId-om
    func navigateToHome(){
        self.navigationController?.pushViewController(CultureTableViewController(), animated: true)
        
    }
    
    
}

extension LoginViewController {
    func loginUserWith(username: String, password: String) {

        let parameters: [String: String] = [
            "username": username,
            "password": password
        ]

        let urlStr = baseUrlString + "/api/auth/login"

        AF.request(urlStr,method: .post, parameters: parameters, encoding: JSONEncoding.default)
            .validate()
            .responseDecodable(of: UserModel.self) { response in
                switch response.result {
                case .success(let user):
                    UserCredentials.shared.setToken(with: user.token)
                    self.userModel = user
                    self.navigateToHome()
                case .failure(let err):
                    print(err)
                }
            }




    }
}
