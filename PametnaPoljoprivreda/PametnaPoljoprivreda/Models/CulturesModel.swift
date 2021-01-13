//
//  CulturesModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.01.2021..
//

import Foundation

struct CulturesModel: Decodable {
    let cultureId: Int
    let title: String
    var devices: [DeviceModel]
    let description: String
}

struct AllCultures: Decodable {
    let cultures: [CulturesModel]
}