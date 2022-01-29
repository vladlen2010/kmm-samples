import SwiftUI
import shared

class BackButtonWrapper: ObservableObject {
    @Published
    var hasButtonBack: Bool = false
}

struct MainView: View {
    
    @ObservedObject
    var model: ObservableValue<MainComponentModel>
    
    @ObservedObject
    var routerState: ObservableValue<RouterState<AnyObject, MainComponentChild>>
    
    let component: MainComponent
    
    init(_ component: MainComponent) {
        self.component = component
        self.routerState = ObservableValue(component.routerState)
        self.model = ObservableValue(component.model)
    }
    
    let tabNames: [String] = ["Posts", "Profile"]
    
    enum Tab: Int {
        case Post
        case Profile
    }
    
    
    
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

//struct MainView_Previews: PreviewProvider {
//    static var previews: some View {
//        MainView(MainPreview())
//            .preferredColorScheme(.light)
//        MainView(MainPreview())
//            .preferredColorScheme(.light)
//    }
//    
//    class MainPreview : MainComponent {
//        
//        var model: Value<MainComponentModel> = mutableValue(
//            MainComponentModel(selectedTab: MainComponentTab.postRoot)
//        )
//        
//        var routerState: Value<RouterState<AnyObject, MainComponentChild>> = simpleRouterState(
//            
//        )
//        
//        func onTabClicked(tab: MainComponentTab) {}
//    }
//}
