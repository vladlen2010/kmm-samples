import SwiftUI
import shared

class BackButtonWrapper: ObservableObject {
    @Published
    var hasButtonBack: Bool = false
}

struct MainView: View {
    
    @ObservedObject
    private var model: ObservableValue<MainModel>
    
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, MainChild>>
    
    private let component: Main
    
    @StateObject
    var permissionsBack = BackButtonWrapper()
    
    @StateObject
    var documentsBack = BackButtonWrapper()
    
    @StateObject
    var walletBack = BackButtonWrapper()
    
    let tabNames: [String] = ["Registrations", "My Documents", "Balance"]
    
    enum Tab: Int {
        case PermissionsTab
        case DocumentsTab
        case WalletTab
    }
    
    init(_ component: Main) {
        self.component = component
        self.routerStates = ObservableValue(component.routerState)
        self.model = ObservableValue(component.model)
    }
    
    
    var body: some View {
        let model = self.model.value
        let child = self.routerStates.value.activeChild.instance
        
        let selectedTab = Binding<Int>(get: {
            switch model.selectedTab {
            case MainTab.permissionTabs:
                return Tab.PermissionsTab.rawValue
            case MainTab.documentTabs:
                return Tab.DocumentsTab.rawValue
            case MainTab.wallet:
                return Tab.WalletTab.rawValue
            default:
                return Tab.PermissionsTab.rawValue
            }
        }) { newValue in
            switch newValue {
            case Tab.PermissionsTab.rawValue:
                component.onTabClick(tab_: MainTab.permissionTabs)
            case Tab.DocumentsTab.rawValue:
                component.onTabClick(tab_: MainTab.documentTabs)
            case Tab.WalletTab.rawValue:
                component.onTabClick(tab_: MainTab.wallet)
            default:
                component.onTabClick(tab_: MainTab.permissionTabs)
            }
        }
        
        
        let hasBackButton = Binding<Bool>(get: {
            switch model.selectedTab {
            case MainTab.permissionTabs:
                return permissionsBack.hasButtonBack
            case MainTab.documentTabs:
                return documentsBack.hasButtonBack
            case MainTab.wallet:
                return walletBack.hasButtonBack
            default:
                return permissionsBack.hasButtonBack
            }
        }, set: { _ in
            //Nothing
        })
        
        NavigationView {
            TabView(selection: selectedTab) {
                
                VStack {
                    switch child {
                    case let permissions as MainChild.Permissions:
                        PermissionTabsView(permissions.component)
                            .onAppear() {
                                // Subscribe so we can start listening for router in the local component changes
                                permissions.component.routerState.subscribe { (routerState) in
                                    if (((routerState.activeChild.instance as? PermissionTabsChild.Web) != nil) ||
                                        ((routerState.activeChild.instance as? PermissionTabsChild.Friends) != nil) ||
                                        ((routerState.activeChild.instance as? PermissionTabsChild.Work) != nil)) {
                                        permissionsBack.hasButtonBack = false
                                    } else {
                                        permissionsBack.hasButtonBack = true
                                    }
                                }
                            }
                    default:
                        EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "house.fill")
                    Text(tabNames[0])
                }
                
                .tag(Tab.PermissionsTab.rawValue)
                
                VStack {
                    switch child {
                    case let documents as MainChild.Documents:
                        DocumentTabsView(documents.component)
                            .onAppear() {
                                documents.component.routerState.subscribe { (routerState) in
                                    if (((routerState.activeChild.instance as? DocumentTabsChild.Documents) != nil) ||
                                        ((routerState.activeChild.instance as? DocumentTabsChild.Applications) != nil)) {
                                        permissionsBack.hasButtonBack = false
                                    } else {
                                        permissionsBack.hasButtonBack = true
                                    }
                                }
                            }
                    default:
                        EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "pencil")
                    Text(tabNames[1])
                }
                .tag(Tab.DocumentsTab.rawValue)
                
                VStack {
                    switch child {
                    case let wallet as MainChild.Wallet:
                        WalletPageView(wallet.component)
                    default:
                        EmptyView()
                    }
                }
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text(tabNames[2])
                }
                .tag(Tab.WalletTab.rawValue)
            }
            .onAppear {
                UITabBar.appearance().backgroundColor = .blue
                UITabBar.appearance().unselectedItemTintColor = .lightGray
            }
            .accentColor(.white)
            .navigationBarTitle(getTitle(selectedTab: selectedTab), displayMode: .inline)
            .navigationBarItems(leading: Button(action: {
                backPressed()
            }) {
                getNavBarView(hasBackButton: hasBackButton)
            })
        }
    }
    
    // Adjust title based on selected tab
    func getTitle(selectedTab: Binding<Int>) -> String {
        switch selectedTab.wrappedValue {
        case Tab.PermissionsTab.rawValue:
            return "\(tabNames[0])"
        case Tab.DocumentsTab.rawValue:
            return "\(tabNames[1])"
        case Tab.WalletTab.rawValue:
            return "\(tabNames[2])"
        default:
            return ""
        }
    }
    
    // Handle backpress action
    func backPressed() {
//        let child = self.routerStates.value.activeChild.instance
//        switch child {
//        case let permissionsTab as MainChild.Permissions:
//
//            if (let web = permissionsTab.component.routerState.value.activeChild.instance as? PermissionTabsChild.Web) {
//                web?.component.hasButtonBack
//            }
//        case let documentsTab as MainChild.Documents:
//            //
//        case let wallet as MainChild.Wallet:
//            //
//        default:
//            print("ignore press")
//        }
    }
    
    // Show back button if needed
    @ViewBuilder
    func getNavBarView(hasBackButton: Binding<Bool>) -> some View {
        if (hasBackButton.wrappedValue) {
            HStack {
                Image(systemName: "chevron.left")
                Text("Back")
            }
        }
        else {
            EmptyView()
        }
    }
    
    
}



//#if DEBUG
//struct MainView_Previews: PreviewProvider {
//    static var previews: some View {
//        MainView()
//    }
//}
//#endif
