//
//  Extensions.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 04.12.2020..
//

import Foundation
import UIKit

extension UIButton {
    static func styleButton(button: UIButton) {
        button.backgroundColor = #colorLiteral(red: 0.721568644, green: 0.8862745166, blue: 0.5921568871, alpha: 1)
        button.layer.cornerRadius = 20
        button.setTitleColor(#colorLiteral(red: 0, green: 0, blue: 0.5019607843, alpha: 1), for: .normal)
    }
}

extension UITextField {
    static func styleTextField(textfield: UITextField) {
        let bottomLine = CALayer()
        bottomLine.frame = CGRect(x: 0, y: textfield.frame.height - 2, width: textfield.frame.width, height: 2)
        bottomLine.backgroundColor = #colorLiteral(red: 0.3411764801, green: 0.6235294342, blue: 0.1686274558, alpha: 1)
        bottomLine.cornerRadius = 20
        textfield.borderStyle = .none
        textfield.layer.addSublayer(bottomLine)
    }
}

extension UILabel {
    static func styleLabel(label: UILabel){
        let bottomLine = CALayer()
        bottomLine.frame = CGRect(x: 0, y: label.frame.height - 2, width: label.frame.width, height: 2)
        bottomLine.backgroundColor = #colorLiteral(red: 0.3411764801, green: 0.6235294342, blue: 0.1686274558, alpha: 1)
        bottomLine.cornerRadius = 20
        label.layer.addSublayer(bottomLine)
        label.layer.masksToBounds = true
    }
}

extension UIViewController {
    func showAlert(title: String, message: String){
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let action = UIAlertAction(title: "OK", style: .default, handler: nil)
        alert.addAction(action)
        self.present(alert, animated: true, completion: nil)
    }
}





