import SwiftUI
import shared


struct DocumentListView: View {
    @ObservedObject
    private var models: ObservableValue<DocumentListModel>
    
    private let component: DocumentList
    
    
    init(_ component: DocumentList) {
        self.models = ObservableValue(component.model)
        self.component = component
    }
    
    
    var body: some View {
        
        let model = self.models.value
        
            List(model.documents, id: \.self) { document in
                
                Button(action: {
                    component.onItemClicked(itemId: document.id)
                }, label: {
                    Text(document.title)
                })
            }
            .listStyle(.inset)
    }
}

#if DEBUG
struct DocumentListView_Previews: PreviewProvider {
    static var previews: some View {
        DocumentListView(DocumentListPreview())
            .preferredColorScheme(.light)
        DocumentListView(DocumentListPreview())
            .preferredColorScheme(.dark)
    }
    
    class DocumentListPreview : DocumentList {
        
        
        let model: Value<DocumentListModel> = mutableValue(
            DocumentListModel(
                documents: [
                    Document(
                        id: "1",
                        title: "Document 1"
                    ),
                    Document(
                        id: "2",
                        title: "Document 2"
                    ),
                    Document(
                        id: "3",
                        title: "Document 3"
                    )
                ]
            )
        )
        
        func onItemClicked(itemId: String) {}
    }
}
#endif
