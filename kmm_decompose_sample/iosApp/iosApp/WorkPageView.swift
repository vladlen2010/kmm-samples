import SwiftUI
import shared

struct WorkPageView: View {
    private let component: WorkPage
    
    init(_ component: WorkPage) {
        self.component = component
    }
    
    var body: some View {
        PermissionListView(component.permissionList)
    }
}

#if DEBUG
struct WorkPageView_Previews: PreviewProvider {
    static var previews: some View {
        WorkPageView(WorkPagePreview())
            .preferredColorScheme(.light)
        WorkPageView(WorkPagePreview())
            .preferredColorScheme(.dark)
    }
    
    class WorkPagePreview : WorkPage {
        var permissionList: PermissionList = PermissionList_Previews.PermissionListPreview()
    }
}
#endif
