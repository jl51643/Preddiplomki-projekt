//
//  CulturesModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.01.2021..
//

import Foundation

class CulturesModel: Decodable {
    let cultureId: Int
    let title: String
    var devices: [DeviceModel]
    let description: String
}


