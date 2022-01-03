import SwiftUI
import shared

struct DocumentDetailsView: View {
    
    private let component: DocumentDetails
    
    init(_ component: DocumentDetails) {
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
struct DocumentDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        DocumentDetailsView(DocumentDetailsPreview())
            .preferredColorScheme(.light)
        DocumentDetailsView(DocumentDetailsPreview())
            .preferredColorScheme(.dark)
    }
    
    class DocumentDetailsPreview: DocumentDetails {
        func onBackPressed_() {}
    }
}
#endif

