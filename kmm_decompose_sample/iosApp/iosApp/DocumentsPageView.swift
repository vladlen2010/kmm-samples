import SwiftUI
import shared

struct DocumentsPageView: View {
    
    private let component: DocumentsPage
    
    init(_ component: DocumentsPage){
        self.component = component
    }
    
    var body: some View {
        VStack {
            DocumentListView(component.documentList)
            
        }
    }
}

#if DEBUG
struct DocumentsPageView_Previews: PreviewProvider {
    static var previews: some View {
        DocumentsPageView(DocumentPagePreview())
            .preferredColorScheme(.light)
        DocumentsPageView(DocumentPagePreview())
            .preferredColorScheme(.dark)
        
    }
    
    class DocumentPagePreview : DocumentsPage {
        var documentList: DocumentList = DocumentListView_Previews.DocumentListPreview()
        func onAddDocumentClicked() {}
    }
}
#endif
