//
//  MesurmentService.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation

class MeasurementsService {
    
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
    
}
