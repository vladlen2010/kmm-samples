import SwiftUI
import shared

struct AddDocumentView: View {
    
    private let component: AddDocumentPage
    
    init(_ component: AddDocumentPage) {
        self.component = component
    }
    
    var body: some View {
        NavigationView {
            VStack {
                Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
            }
            .navigationBarItems(leading: Button(action: {
                component.onBackPressed_()
            }) {
                HStack {
                    Image(systemName: "chevron.left")
                    Text("Back")
                }
            })
        }
    }
}

#if DEBUG
struct AddDocument_Previews: PreviewProvider {
    static var previews: some View {
        AddDocumentView(AddDocumentPreview())
            .preferredColorScheme(.light)
        AddDocumentView(AddDocumentPreview())
            .preferredColorScheme(.dark)
    }
    
    class AddDocumentPreview : AddDocumentPage {
        func onBackPressed_() {}
    }
}
#endif
