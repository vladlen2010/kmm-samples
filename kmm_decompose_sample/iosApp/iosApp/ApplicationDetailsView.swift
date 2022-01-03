import SwiftUI
import shared

struct ApplicationDetailsView: View {
    
    private let component: ApplicationDetails
    
    init(_ component: ApplicationDetails) {
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
struct ApplicationDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationDetailsView(ApplicationDetailsPreview())
            .preferredColorScheme(.light)
        ApplicationDetailsView(ApplicationDetailsPreview())
            .preferredColorScheme(.dark)
    }
    
    class ApplicationDetailsPreview: ApplicationDetails {
        func onBackPressed_() {}
    }
}
#endif

