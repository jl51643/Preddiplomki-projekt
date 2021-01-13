//
//  DeviceViewModel.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 13.01.2021..
//

import Foundation

class DeviceViewModel {
    
    var devices: [DeviceModel]?
    
    func addDevice(){
        
    }
    
    func deleteDevic(cultureID: Int, devID: Int){
        let servis = MeasurementsService()
        
        servis.deleteDeviceFromCulture(cultureID: cultureID, devID: devID)
    }
    
   
}
