//
//  DeviceTableViewCell.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 13.01.2021..
//

import UIKit

class DeviceTableViewCell: UITableViewCell {
    @IBOutlet weak var idLabel: UILabel!
    @IBOutlet weak var devIDLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    override func prepareForReuse() {
        idLabel.text = ""
        devIDLabel.text = ""
    }
    
    func configure(with device: DeviceCellModel){
        self.idLabel.text = device.id
        self.devIDLabel.text = device.devID
    }
}
