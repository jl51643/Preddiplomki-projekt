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
    let devices: [DeviceModel]
    let description: String
}
