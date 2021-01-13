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
    private var viewModel: CulturesViewModel
    
    init(viewModel: CulturesViewModel) {
        self.viewModel = viewModel
        super.init(nibName: "DevicesViewController", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
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
        let servis = MeasurmentsService()
        
        servis.deleteDeviceFromCulture(cultureID: cultureID, devID: devID) { result in
            switch result {
            case.success(let cultures):
                self.viewModel.cultures = cultures
                self.refresh()
            case .failure(let error):
                debugPrint(error)
            }
        }
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
        if let cultureID = viewModel.selectedCulture?.cultureId {
            let addDeviceVC = AddDeviceViewController(viewModel: viewModel)
            addDeviceVC.delegate = self
            navigationController?.present(addDeviceVC, animated: true, completion: nil)
        }
    }
}

extension DevicesViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    } // visina jednog cella
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            guard let id = viewModel.selectedCulture?.devices[indexPath.row].id,
                  let cultureID = viewModel.selectedCulture?.cultureId,
                  let IDInt = try? Int(id) else {  return}
            viewModel.selectedCulture?.devices.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .top)
            deleteDevice(cultureID: cultureID, devID: IDInt)
            
        } else if editingStyle == .insert {
            print("nije delete")
        }
    }


}

extension DevicesViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.selectedCulture?.devices.count ?? 0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
       if let cell = tableView.dequeueReusableCell(withIdentifier: Constants.deviceCellReuseIdentifier, for: indexPath) as? DeviceTableViewCell {
        if let device = viewModel.selectedCulture?.devices[indexPath.row] {
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
