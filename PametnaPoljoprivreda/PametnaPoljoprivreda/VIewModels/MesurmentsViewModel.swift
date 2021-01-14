//
//  MesurmentsViewModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation
import SVProgressHUD

class MeasurementsViewModel {
    var measurements: [MeasurementsModel] = []
    
    func fetchMeasurments(completion: @escaping ((Result<Void, Error>)->Void)) {
        let measurmentsService = MeasurmentsService()
        
        measurmentsService.fetchMeasurmentData { (result) in
            switch result {
            case .success(let measurments):
                self.measurements = measurments
                completion(.success(()))
            case .failure(let error):
                self.measurements = []
                completion(.failure(error))
            }
        }

    }
    
//    func getTempData() -> [Double?]? {
//
//      return measurements?.map({ (m) -> Double? in
//        return m.airTemperature
//      })
//    }
    
    
}
