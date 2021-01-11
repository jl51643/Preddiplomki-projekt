//
//  MesurmentService.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation

class MeasurementsService {
    
    let baseUrlString = "http://ad8c14c1e2c4.ngrok.io"
    
    func fetchMeasurmentData(completion: @escaping ((Result<[MeasurementsModel], Error>)->Void)){
    
        let urlString = "https://f70907d076b7.ngrok.io/api/measurement/all"
        guard let url = URL(string: urlString) else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
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
        
        let token = UserCredentials.shared.getToken()
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.addValue("Bearer \(String(describing: token))", forHTTPHeaderField: "Authorization")
        
        
        URLSession.shared.dataTask(with: request) { (data,response,error) in
            if let data = data {
                do {
                    let model = try JSONDecoder().decode([CulturesModel].self, from: data)
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
    
}
