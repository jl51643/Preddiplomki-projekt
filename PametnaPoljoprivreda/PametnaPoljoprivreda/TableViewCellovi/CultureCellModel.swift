//
//  CultureCellModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 12.01.2021..
//

import Foundation
struct CultureCellModel {
    let title: String
    let description: String
    
    init(culture: CulturesModel) {
        self.title = culture.title
        self.description = culture.description
    }
        
}
