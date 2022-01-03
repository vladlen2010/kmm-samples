import SwiftUI
import shared

struct RootView: View {
    
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, RootChild>>
    
    init(_ component: Root) {
        self.routerStates = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerStates.value.activeChild.instance
        
        switch child {
        case let main as RootChild.Main:
            MainView(main.component)
        case let permissionDetails as RootChild.PermissionDetails:
            PermissionDetailsView(permissionDetails.component)
        case let documentDetails as RootChild.DocumentDetails:
            DocumentDetailsView(documentDetails.component)
        case let applicationDetails as RootChild.ApplicationDetails:
            ApplicationDetailsView(applicationDetails.component)
        case let addDocument as RootChild.AddDocument:
            AddDocumentView(addDocument.component)
        default:
            EmptyView()
        }
    }
}

//#if DEBUG
//struct RootView_Previews: PreviewProvider {
//    static var previews: some View {
//        RootView(RootViewPreview())
//            .preferredColorScheme(.light)
//        RootView(RootViewPreview())
//            .preferredColorScheme(.light)
//    }
//    
//    class RootViewPreview : Root {
//        var routerState: Value<RouterState<AnyObject, RootChild>> = simpleRouterState(
//            RootChild.Main(component:
//                            MainView_Previews.
//            )
//        )
//    }
//}
//#endif
