//
//  UserCredentials.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 07.01.2021..
//

import Foundation

final class UserCredentials {
    private var token: String?
    
    private init(){}
    static let shared = UserCredentials()
    
    func setToken(with token: String){
        self.token = token
    }
    
    func getToken() -> String? {
        return token
    }
}
