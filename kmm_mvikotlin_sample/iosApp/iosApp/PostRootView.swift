import SwiftUI
import shared

struct PostRootView: View {
    
    @ObservedObject
    var routerState: ObservableValue<RouterState<AnyObject, PostRootComponentChild>>
    
    init(_ component: PostRootComponent) {
        self.routerState = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = self.routerState.value.activeChild.instance
        
        switch child {
        case let list as PostRootComponentChild.PostList:
            PostListView(list.component)
        case let details as PostRootComponentChild.PostDetails:
            PostDetailsView(details.component)
        default:
            fatalError()
        }
    }
}

#if DEBUG
struct PostRoot_Previews: PreviewProvider {
    static var previews: some View {
        PostRootView(PostRootPreview())
            .preferredColorScheme(.light)
        PostRootView(PostRootPreview())
            .preferredColorScheme(.light)
    }
    
    class PostRootPreview : PostRootComponent {
        var routerState: Value<RouterState<AnyObject, PostRootComponentChild>> = simpleRouterState(
            PostRootComponentChild.PostDetails(component: PostDetailsView_Previews.PostDetailPreview())
        )
    }
}
#endif
