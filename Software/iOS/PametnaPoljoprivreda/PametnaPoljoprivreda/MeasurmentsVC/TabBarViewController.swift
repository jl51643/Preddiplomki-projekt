//
//  TabBarViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import UIKit

class TabBarViewController: UITabBarController {
    
    var viewModel: MeasurementsViewModel?
    var tempData: [Double] = []
    
    var array: [MeasurementsModel]?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setUpTabBar()
       
    }
    

    func setUpTabBar() {
    
        let layer = CAShapeLayer()
        layer.path = UIBezierPath(roundedRect: CGRect(x: 30, y: self.tabBar.bounds.minY + 5, width: self.tabBar.bounds.width - 60, height: self.tabBar.bounds.height + 10), cornerRadius: (self.tabBar.frame.width/2)).cgPath
        layer.borderWidth = 1.0
        layer.opacity = 1.0
        layer.isHidden = false
        layer.masksToBounds = false
        layer.fillColor = #colorLiteral(red: 0.2745098174, green: 0.4862745106, blue: 0.1411764771, alpha: 1)
        self.tabBar.layer.insertSublayer(layer, at: 0)
        
        tabBar.barTintColor = #colorLiteral(red: 0.9999960065, green: 1, blue: 1, alpha: 1)
        tabBar.tintColor = #colorLiteral(red: 0.9607843161, green: 0.7058823705, blue: 0.200000003, alpha: 1)
        tabBar.unselectedItemTintColor = #colorLiteral(red: 0.9999960065, green: 1, blue: 1, alpha: 1)
    
        
        let temperatureVC = TemperatureViewController()
        let humidityVC = HumidityViewController()
        let soilTemperatureVC = SoilTemperatureViewController()
        let soilHumidityVC = SoilHumidityViewController()
    
        
        
        let tempBarItem = UITabBarItem(title: "Temperature", image: UIImage(named: "temp"), tag: 0)
        let humidityBarItem = UITabBarItem(title: "Humidity", image: UIImage(named: "hum"), tag: 1)
        let soilTempBarItem = UITabBarItem(title: "Soil Temperature", image: UIImage(named: "sTemp"), tag: 2)
        let soilHumidityBarItem = UITabBarItem(title: "Soil Humidity", image: UIImage(named: "sHum"), tag: 3)
        
        temperatureVC.tabBarItem = tempBarItem
        humidityVC.tabBarItem = humidityBarItem
        soilTemperatureVC.tabBarItem = soilTempBarItem
        soilHumidityVC.tabBarItem = soilHumidityBarItem
        
        viewControllers = [temperatureVC, humidityVC, soilTemperatureVC, soilHumidityVC]
        
        if let items = self.tabBar.items {
          items.forEach { item in item.imageInsets = UIEdgeInsets(top: 5, left: 0, bottom: -5, right: 0) }
        }

        self.tabBar.itemWidth = 35.0
        self.tabBar.itemPositioning = .centered
    
    }
    
}
