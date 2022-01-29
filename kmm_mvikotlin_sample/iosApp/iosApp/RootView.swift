import SwiftUI
import shared

struct RootView: View {
    
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, RootComponentChild>>
    
    init(_ component: RootComponent) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        
        switch child {
        case let main as RootComponentChild.Main:
            MainView(main.component)
        default:
            EmptyView()
        }
    }
}
