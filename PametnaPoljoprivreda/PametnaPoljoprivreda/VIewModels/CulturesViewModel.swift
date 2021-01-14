//
//  CulturesViewModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.01.2021..
//

import Foundation

class CulturesViewModel {
    var cultures: [CulturesModel] = []
    var selectedCulture: CulturesModel?
    var selectedIndex: Int?
    
    func fetchCultures(completion: @escaping ((Result<Void, Error>)->Void)){
        let service = MeasurmentsService()
        
        service.fetchCultures { (result) in
            switch result {
            case .success(let cultures):
                self.cultures = cultures
                completion(.success(()))
            case .failure(let error):
                self.cultures = []
                completion(.failure(error))
            }
        }
    }
    
    func culturesForTVC(forIndexPath indexpath: IndexPath) -> CultureCellModel? {
        return CultureCellModel(culture: cultures[indexpath.row])
    }
    
    func deleteCulture(cultureID: Int){
        let service = MeasurmentsService()
        service.deleteCulture(cultureID: cultureID)
    }
}
