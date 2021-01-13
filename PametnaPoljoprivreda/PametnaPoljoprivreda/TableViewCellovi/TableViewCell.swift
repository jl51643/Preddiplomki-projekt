//
//  TableViewCell.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 07.01.2021..
//

import UIKit

class TableViewCell: UITableViewCell {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    override func prepareForReuse() {
        nameLabel.text = ""
        descriptionLabel.text = ""
    }
    
    func configure(with culture: CultureCellModel ){
        self.nameLabel.text = culture.title
        self.descriptionLabel.text = culture.description
    }
}
