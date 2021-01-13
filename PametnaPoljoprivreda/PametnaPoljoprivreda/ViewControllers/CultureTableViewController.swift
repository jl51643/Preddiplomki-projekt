//
//  CultureTableViewController.swift
//  PametnaPoljoprivreda
//
//  Created by Borna on 11.01.2021..
//

import UIKit
import Alamofire

class CultureTableViewController: UIViewController {
    
    @IBOutlet var tableView: UITableView!
    
    private var viewModel: CulturesViewModel?
    private var refreshControl: UIRefreshControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupNavigationBar()
        setupTableView()
        setUpViewModel()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.refresh()
    }

    func setupNavigationBar() {
        navigationController?.navigationBar.isHidden = false
        //navigationController?.navigationItem.hidesBackButton = true
        navigationController?.title = "Cultures"
        let button = UIBarButtonItem(image: UIImage(systemName: "plus"), style: .plain, target: self, action: #selector(CultureTableViewController.didSelectAddCulture))
        navigationItem.rightBarButtonItem = button
        navigationItem.leftBarButtonItem = button
    }

    
    func setUpViewModel(){

        viewModel = CulturesViewModel()
        
        viewModel?.fetchCultures(completion: { (result) in
            DispatchQueue.main.async {
                switch result {
                case .success(let model):
                    self.viewModel?.cultures = model
                    self.refresh()
                case .failure(let err):
                    self.showAlert(title: "Error", message: err.localizedDescription)
                }
            }
        })
    }
    
    
    
  
    func setupTableView() {
       
        tableView.delegate = self
        tableView.dataSource = self
      
        
        refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(CultureTableViewController.refresh), for: UIControl.Event.valueChanged)
        tableView.refreshControl = refreshControl
        
        tableView.register(UINib(nibName: "TableViewCell", bundle: nil), forCellReuseIdentifier: Constants.cellReuseIdentifier)
    }
    
    @objc func refresh() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
        }
    }

    @objc func didSelectAddCulture() {
        let addCultureVC = AddCultureViewController()
        addCultureVC.resultDelagate = self
        navigationController?.present(addCultureVC, animated: true, completion: nil)
    }
    
   

  
    
}

extension CultureTableViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    } // visina jednog cella
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            guard let cultureToDelete = viewModel?.cultures?[indexPath.row] else{return}
            viewModel?.cultures?.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
            viewModel?.deleteCulture(cultureID: cultureToDelete.cultureId)
        } else if editingStyle == .insert {
            print("nije delete")
        }
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)

        UIView.beginAnimations("flipajVC", context: nil)
        UIView.setAnimationDuration(1.0)

        guard let viewModel = viewModel else {
            return
        }
      
        let cultureVC = OneCultureViewController(model: (viewModel.cultures?[indexPath.row])!)
        navigationController?.pushViewController(cultureVC, animated: false)

        UIView.setAnimationTransition(UIView.AnimationTransition.flipFromLeft, for: (self.navigationController?.view)!, cache: false)
        UIView.commitAnimations()
    }//na tap cella prijedi na quizvc
}

extension CultureTableViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel?.cultures?.count ?? 0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
       if let cell = tableView.dequeueReusableCell(withIdentifier: Constants.cellReuseIdentifier, for: indexPath) as? TableViewCell {
           if let culture = viewModel?.culturesForTVC(forIndexPath: indexPath) {
               cell.configure(with: culture)
           }
           return cell
       }

       return TableViewCell()
   }
    
}

extension CultureTableViewController: AddCultureDelegate {
    func didAddCulture() {
        self.setUpViewModel()
        self.refresh()
    }


}
