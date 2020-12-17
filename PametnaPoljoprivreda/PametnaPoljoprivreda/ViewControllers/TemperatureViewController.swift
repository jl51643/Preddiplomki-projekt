//
//  TemperatureViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 16.12.2020..
//

import UIKit
import Charts
import SVProgressHUD

class TemperatureViewController: UIViewController, ChartViewDelegate {
    
    private var chartView = LineChartView()
    private var viewModel: MeasurementsViewModel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = "Temperature"
       
        setUpViewModel()
    }
    
    
    
    func setUpCharth(arr: [Double]){
        chartView.delegate = self
        
        chartView.frame = CGRect(x: 0, y: 0, width: 350, height: 400)
        chartView.center = view.center
        view.addSubview(chartView)
        
        
        let xAxisLimit = ChartLimitLine(limit: 10)
        xAxisLimit.lineWidth = 2
        
        let leftAxis = chartView.leftAxis
        leftAxis.removeAllLimitLines()
        leftAxis.axisMinimum = 0
        leftAxis.axisMaximum = 60
        chartView.rightAxis.enabled = false
        
    
        chartView.legend.form = .line
        
        
        //data
        var entries = [ChartDataEntry]()
        

    
        for x in 0 ..< arr.count {
            entries.append(ChartDataEntry(x: Double(x), y: Double(arr[x])))
        }
        
        let set = LineChartDataSet(entries: entries, label: "Humidity")
        set.colors = ChartColorTemplates.pastel()
        
       
        set.fillColor = #colorLiteral(red: 0.2745098174, green: 0.4862745106, blue: 0.1411764771, alpha: 1)
        set.drawFilledEnabled = true
    
        
        let data = LineChartData(dataSet: set)
        chartView.data = data
    }
    
    
    func setUpViewModel(){
        SVProgressHUD.show()
        let servis = MeasurementsService()
        servis.fetchMeasurmentData { (result) in
            switch result {
            case .success(let model):
                self.viewModel?.measurements = model
                let arr = model.map { (m) -> Double in
                    return (m.airTemperature ?? 1)
                }
                self.setUpCharth(arr: arr)
                SVProgressHUD.dismiss(withDelay: TimeInterval(5))
            case .failure(_):
                SVProgressHUD.showError(withStatus: "Error while fetching data")
            }
        }
    

    }
    

}
