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
    private var array: [Double]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //setUpViewModel()
        setUpCharth(array: array)
    }
    
    init(array: [Double]) {
        self.array = array
        super.init(nibName: "TemperatureViewController", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
    
    
    func setUpCharth(array: [Double]){
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
        
        var entries = [ChartDataEntry]()
    
        for x in 0 ..< array.count {
            entries.append(ChartDataEntry(x: Double(x), y: Double(array[x])))
        }
        
        let set = LineChartDataSet(entries: entries, label: "Temperature")
        set.colors = ChartColorTemplates.pastel()
        
        set.fillColor = #colorLiteral(red: 0.2745098174, green: 0.4862745106, blue: 0.1411764771, alpha: 1)
        set.drawFilledEnabled = true
    
        let data = LineChartData(dataSet: set)
        chartView.data = data
    }
    
    
//    func setUpViewModel(){
//        //SVProgressHUD.show()
////        let servis = MeasurmentsService()
////        servis.fetchMeasurmentData { (result) in
////            switch result {
////            case .success(let model):
////                self.viewModel?.measurements = model
////                let arr = model.map { (m) -> Double in
////                    return (m.airTemperature ?? 1)
////                }
////                self.setUpCharth(arr: arr)
////                SVProgressHUD.dismiss(withDelay: TimeInterval(5))
////            case .failure(_):
////                SVProgressHUD.showError(withStatus: "Error while fetching data")
////            }
////        }
//
//        viewModel = MeasurementsViewModel()
//
//        viewModel?.fetchMeasurments(completion: { (result) in
//            switch result {
//            case .success():
//                if let array = self.viewModel?.measurements.map({ (m) -> Double in
//                    return (m.airTemperature ?? 1)
//                }){
//                    self.setUpCharth(arr: array)}
//            case .failure(let err):
//                self.showAlert(title: "Error", message: err.localizedDescription)
//            }
//        })
//
//
//    }
    

}
