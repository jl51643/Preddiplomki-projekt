//
//  Measurement.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import Foundation

struct MeasurementsModel: Decodable {
    let id: Int?
    let device: DeviceModel?
    let time: String?
    let airHumidity: Double?
    let soilHumidity: Double?
    let airTemperature: Double?
    let soilTemperature: Double?
    let pressure: Double?
}

