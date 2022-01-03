import SwiftUI
import shared

struct ApplicationsPageView: View {
    
    private let component: ApplicationPage
    
    init(_ component: ApplicationPage) {
        self.component = component
    }
    
    var body: some View {
        ApplicationListView(component.applicationList)
    }
}


#if DEBUG
struct ApplicationsPageView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationsPageView(ApplicationsPagePreview())
            .preferredColorScheme(.light)
        ApplicationsPageView(ApplicationsPagePreview())
            .preferredColorScheme(.dark)
    }
    
    class ApplicationsPagePreview : ApplicationPage {
        var applicationList: ApplicationList = ApplicationListView_Previews.ApplicationListPreview()
        func onAddDocumentClicked() {}
    }
}
#endif
