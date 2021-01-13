//
//  DevicesViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 13.01.2021..
//

import UIKit

class DevicesViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!

    private var refreshControl: UIRefreshControl!
    
    var model: [DeviceModel]? {
        didSet{
            self.refresh()
        }
    }
    var cultureID: Int?
    
    convenience init(model: [DeviceModel], cultureID: Int){
        self.init()
        self.model = model
        self.cultureID = cultureID
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupTableView()
        setupNavigationBar()

    }
    override func viewDidAppear(_ animated: Bool) {
        self.refresh()
    }
   
    
    func setupNavigationBar(){
        navigationController?.title = "Devices"
        let button = UIBarButtonItem(image: UIImage(systemName: "plus"), style: .plain, target: self, action: #selector(DevicesViewController.didSelectAddDevice))
        navigationItem.rightBarButtonItem = button
    
    }
    
    func deleteDevice(cultureID: Int, devID: Int){
        let servis = MeasurementsService()
        
        servis.deleteDeviceFromCulture(cultureID: cultureID, devID: devID)
    }
    
    func setupTableView() {
       
        tableView.delegate = self
        tableView.dataSource = self
      
        
        refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(CultureTableViewController.refresh), for: UIControl.Event.valueChanged)
        tableView.refreshControl = refreshControl
        
        tableView.register(UINib(nibName: "DeviceTableViewCell", bundle: nil), forCellReuseIdentifier: Constants.deviceCellReuseIdentifier)
    }

    @objc func refresh() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
        }
    }

    @objc func didSelectAddDevice() {
        guard let cultureId = cultureID else {return}
        let addDeviceVC = AddDeviceViewController(cultureId: cultureId)
        addDeviceVC.delegate = self
        navigationController?.present(addDeviceVC, animated: true, completion: nil)
    }
}

extension DevicesViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    } // visina jednog cella
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            guard let id = model?[indexPath.row].id,
                  let cultureID = cultureID,
                  let IDInt = try? Int(id) else {  return}
            model?.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .top)
            deleteDevice(cultureID: cultureID, devID: IDInt)
            
        } else if editingStyle == .insert {
            print("nije delete")
        }
    }


}

extension DevicesViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model?.count ?? 0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
       if let cell = tableView.dequeueReusableCell(withIdentifier: Constants.deviceCellReuseIdentifier, for: indexPath) as? DeviceTableViewCell {
        if let device = model?[indexPath.row] {
            cell.configure(with: DeviceCellModel(id: String(device.id), devId: device.devId))
        }
           return cell
       }

       return TableViewCell()
   }
    
}

extension DevicesViewController: AddDeviceDelagate {
    func didAddDevice() {
        self.refresh()
    }


}
