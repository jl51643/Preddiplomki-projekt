//
//  MesurmentsViewModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation
import SVProgressHUD

class MeasurementsViewModel {
    var measurements: [MeasurementsModel]?
    
    func fetchMeasurments(compleation: @escaping((Result<[MeasurementsModel],Error>)->Void)) {
        let measurmentsService = MeasurmentsService()
        
        measurmentsService.fetchMeasurmentData { (result) in
            DispatchQueue.main.async {
                compleation(result)
            }
        }

    }
    
    func getTempData() -> [Double?]? {

      return measurements?.map({ (m) -> Double? in
        return m.airTemperature
      })
    }
    
    
}
