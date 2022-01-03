import SwiftUI
import shared

struct WebPageView: View {
    
    private let component: WebPage
    
    init(_ component: WebPage) {
        self.component = component
    }
    
    var body: some View {
        PermissionListView(component.permissionList)
    }
}


#if DEBUG
struct WebPageView_Previews: PreviewProvider {
    static var previews: some View {
        WebPageView(WebPagePreview())
            .preferredColorScheme(.light)
        WebPageView(WebPagePreview())
            .preferredColorScheme(.dark)
    }
    
    class WebPagePreview : WebPage {
        var permissionList: PermissionList = PermissionList_Previews.PermissionListPreview()
    }
}
#endif
