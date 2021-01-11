//
//  LoginService.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.12.2020..
//

import Foundation
import Alamofire

class LoginService {
    
    let baseUrlString = "http://ad8c14c1e2c4.ngrok.io"
  
    
    func loginUserWith(username: String, password: String, completion: @escaping ((Result<UserModel, Error>) -> Void)){
        
        let urlString = baseUrlString + "/api/auth/login"
        let json = ["username" : username, "password" : password]

        guard let url = URL(string: urlString) else {return}
        
        let jsonData = try! JSONSerialization.data(withJSONObject: json, options: [])
    
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        URLSession.shared.uploadTask(with: request, from: jsonData){ data,response,error in
            
            if let data = data {
                do {
                    let model = try JSONDecoder().decode(UserModel.self, from: data)
                } catch {
                    print("a")
                }
            }
            
        }.resume()
        
    }
    
    func loginUserWith2(username: String, password: String, completion: @escaping ((Result<UserModel, Error>) -> Void)) {
        
        let urlString = baseUrlString + "/api/auth/login"
        
        guard let url = URL(string: urlString) else { return }
        
        let items = ["username" : username, "password" : password]
        
        var components = URLComponents(url: url, resolvingAgainstBaseURL: true)
        
        var queryItems:[URLQueryItem] = []
        items.forEach({ (key, value) in
            queryItems.append(URLQueryItem(name: key, value: value))
        })
        components?.queryItems = queryItems
        
        if let url = components?.url {
            var request = URLRequest(url:url)
            request.httpMethod = "POST"
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            URLSession.shared.dataTask(with: request) {(data, resp, err) in
                if let data = data {
                    do {
                        let model = try JSONDecoder().decode(UserModel.self, from: data)
                        completion(.success(model))
                    } catch let decodeErr {
                        completion(.failure(decodeErr))
                    }
                }
                if let err = err {
                    completion(.failure(err))
                    return
                }
            }.resume()
        }
    }
    
    func registerUserWith(username: String, email: String, password: String, completion: @escaping ((Result<UserModel, Error>) -> Void)){
        
        let urlString = baseUrlString + "/api/auth/register"
        
        guard let url = URL(string: urlString) else {return}
        
        let data = ["username" : username, "password" : password]
        
        guard let jsonData = try? JSONEncoder().encode(data) else {
            print("error while converting data to json"); return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = jsonData
        
        URLSession.shared.dataTask(with: request) {(data, resp, err) in
            if let data = data {
                do {
                    let model = try JSONDecoder().decode(UserModel.self, from: data)
                    completion(.success(model))
                } catch let decodeErr {
                    completion(.failure(decodeErr))
                }
            }
            if let err = err {
                completion(.failure(err))
                return
            }
        }.resume()
        
        
        
       
    }
    
    func registerUserWith2(username: String, email: String, password: String, completion: @escaping ((Result<UserModel, Error>) -> Void)) {
        
        let urlString = baseUrlString + "/api/auth/register"
        
        guard let url = URL(string: urlString) else { return }
        
        let items = ["username" : username, "email" : email, "password" : password]
        
        var components = URLComponents(url: url, resolvingAgainstBaseURL: true)
        
        var queryItems:[URLQueryItem] = []
        items.forEach({ (key, value) in
            queryItems.append(URLQueryItem(name: key, value: value))
        })
        components?.queryItems = queryItems
        
        if let url = components?.url {
            var request = URLRequest(url:url)
            request.httpMethod = "POST"
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            URLSession.shared.dataTask(with: request) {(data, resp, err) in
                if let data = data {
                    do {
                        let model = try JSONDecoder().decode(UserModel.self, from: data)
                        completion(.success(model))
                    } catch let decodeErr {
                        completion(.failure(decodeErr))
                    }
                }
                if let err = err {
                    completion(.failure(err))
                    return
                }
            }.resume()
        }
    }
    
    


}


