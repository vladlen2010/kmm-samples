import SwiftUI
import shared

struct PermissionTabsView: View {
    
    @ObservedObject
    private var model: ObservableValue<PermissionTabsModel>
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, PermissionTabsChild>>
    
    private let component: PermissionTabs
    
    @State
    var selected: PermissionTabsTab
    
    init(_ component: PermissionTabs) {
        self.model = ObservableValue(component.model)
        self.component = component
        self.routerState = ObservableValue(component.routerState)
        self.selected = component.model.value.selectedTab
    }
    
    enum Tab: Int {
        case WebTab
        case FriendsTab
        case WorkTab
    }
    
    var body: some View {
        let model = model.value
        let child = routerState.value.activeChild.instance
        
        
        let selectedTab = Binding<Int>(get: {
            switch model.selectedTab {
            case PermissionTabsTab.web:
                return Tab.WebTab.rawValue
            case PermissionTabsTab.friends:
                return Tab.FriendsTab.rawValue
            case PermissionTabsTab.work:
                return Tab.WorkTab.rawValue
            default:
                return Tab.WebTab.rawValue
            }
        }) { newValue in
            switch newValue {
            case Tab.WebTab.rawValue:
                component.onChangeTab(tab: PermissionTabsTab.web)
            case Tab.FriendsTab.rawValue:
                component.onChangeTab(tab: PermissionTabsTab.friends)
            case Tab.WorkTab.rawValue:
                component.onChangeTab(tab: PermissionTabsTab.work)
            default:
                component.onChangeTab(tab: PermissionTabsTab.web)
            }
        }
        
        VStack {
            Picker("Permission tabs", selection: selectedTab ) {
                Text("Web")
                    .tag(Tab.WebTab.rawValue)
                Text("Friends")
                    .tag(Tab.FriendsTab.rawValue)
                Text("Work")
                    .tag(Tab.WorkTab.rawValue)
            }
            .pickerStyle(.segmented)
            
            
            switch child {
            case let webChild as PermissionTabsChild.Web:
                WebPageView(webChild.component)
            case let friendsChild as PermissionTabsChild.Friends:
                FriendsPageView(friendsChild.component)
            case let workChild as PermissionTabsChild.Work:
                WorkPageView(workChild.component)
            default:
                EmptyView()
            }
            
        }
    }
}

//#if DEBUG
//struct PermissionPageView_Previews: PreviewProvider {
//    static var previews: some View {
//        PermissionTabsView()
//            .preferredColorScheme(.light)
//        PermissionTabsView()
//            .preferredColorScheme(.dark)
//    }
//}
//#endif
