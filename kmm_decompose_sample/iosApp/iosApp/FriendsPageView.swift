import SwiftUI
import shared

struct FriendsPageView: View {
    private let component: FriendsPage
    
    init(_ component: FriendsPage) {
        self.component = component
    }
    
    var body: some View {
        PermissionListView(component.permissionList)
    }
}

#if DEBUG
struct FriendsPageView_Previews: PreviewProvider {
    static var previews: some View {
        FriendsPageView(FriendsPagePreview())
            .preferredColorScheme(.light)
        FriendsPageView(FriendsPagePreview())
            .preferredColorScheme(.dark)
    }
    
    class FriendsPagePreview : FriendsPage {
        var permissionList: PermissionList = PermissionList_Previews.PermissionListPreview()
    }
}
#endif
