//
//  CulturesViewModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.01.2021..
//

import Foundation

class CulturesViewModel {
    var cultures: [CulturesModel]?
    
    func fetchCultures(completion: @escaping ((Result<[CulturesModel], Error>)->Void)){
        let service = MeasurementsService()
        
        service.fetchCultures { (result) in
            DispatchQueue.main.async {
                completion(result)
            }
        }
    }
}
