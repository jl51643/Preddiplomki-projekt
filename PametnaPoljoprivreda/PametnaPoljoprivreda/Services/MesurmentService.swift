//
//  MesurmentService.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation
import Alamofire

class MeasurmentsService {
    
    let baseUrlString = Constants.baseUrl
    
    func fetchMeasurmentData(completion: @escaping ((Result<[MeasurementsModel], Error>)->Void)){
    
        let urlString = baseUrlString + "/api/measurement/all"
        guard let url = URL(string: urlString) else {return}
        guard let token = UserCredentials.shared.getToken() else {
            return
        }
    
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        
        URLSession.shared.dataTask(with: request) { (data,response,error) in
            if let data = data {
                do {
                    let model = try JSONDecoder().decode([MeasurementsModel].self, from: data)
                    completion(.success(model))
                } catch let decodeError {
                    completion(.failure(decodeError))
                    return
                }
            } else if let error = error {
                completion(.failure(error))
            }
            
        }.resume()
    }
    
    func fetchCultures(completion: @escaping ((Result<[CulturesModel], Error>)->Void)) {

        let urlString = baseUrlString + "/api/culture/all"
        guard let url = URL(string: urlString) else {return}

        guard let token = UserCredentials.shared.getToken() else {return}

        var request = URLRequest(url: url)
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")


        URLSession.shared.dataTask(with: request) { (data,response,error) in
            if let data = data {
                do {
                    let model = try JSONDecoder().decode([CulturesModel].self, from: data)
                    completion(.success(model))
                } catch let decodeError {
                    completion(.failure(decodeError))
                    return
                }
            }
        }.resume()

    }
    
    func addCulture(cultureID: String, title :String, deviceID: String, deviceDevID: String, description: String) {
        
        let urlString = baseUrlString + "/api/culture/add"
        guard let url = URL(string: urlString) else {return}
        
        guard let token = UserCredentials.shared.getToken() else {return}
        
        let data = [
            "cultureId": cultureID,
            "title": title,
            "devices": [
                ["id": deviceID,
                "devId": deviceDevID]
                ],
            "description": description
        ] as [String : Any]
        
        guard let jsonData = try? JSONSerialization.data(withJSONObject: data, options: []) else { return }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = jsonData

        URLSession.shared.dataTask(with: request) { data, response, error in
            if let data = data {
                print(data)
            } else if let err = error {
                print(err.localizedDescription)
            }
        }.resume()
    }
    
    func deleteCulture(cultureID: Int) {
        let urlString = baseUrlString + "/api/culture/delete/\(cultureID)"
        guard let url = URL(string: urlString) else {return}
        
        guard let token = UserCredentials.shared.getToken() else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let data = data {
                print(data)
            } else if let err = error {
                print(err.localizedDescription)
            } else if let response = response {
                print(response)
            }
        }.resume()
    }
    
    func addDeviceToCulture(cultureID: Int, id: String, completion: @escaping ((Result<[CulturesModel], Error>)->Void)) {
        let urlString = baseUrlString + "/api/culture/\(cultureID)/devices/add/\(id)"
        guard let url = URL(string: urlString) else {return}
        
        guard let token = UserCredentials.shared.getToken() else {return}
        
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let data = data {
                print(data)
                self.fetchCultures { result in
                    switch result {
                    case .success(let cultures):
                        completion(.success(cultures))
                    case .failure(let err):
                        completion(.failure(err))
                    }
                }
            } else if let err = error {
                completion(.failure(err))
            }
        }.resume()
    }
    
    func deleteDeviceFromCulture(cultureID: Int, devID: Int, completion: @escaping ((Result<[CulturesModel], Error>)->Void)) {
        let urlString = baseUrlString + "/api/culture/\(cultureID)/devices/delete/\(devID)"
        guard let url = URL(string: urlString) else {return}
        
        guard let token = UserCredentials.shared.getToken() else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let data = data {
                self.fetchCultures { result in
                    switch result {
                    case .success(let cultures):
                        completion(.success(cultures))
                    case .failure(let err):
                        completion(.failure(err))
                    }
                }
            } else if let err = error {
                print(err.localizedDescription)
                completion(.failure(err))
            }
        }.resume()
    }
    
}
