import SwiftUI
import shared

struct PermissionDetailsView: View {
    
    private let component: PermissionDetails
    
    init(_ component: PermissionDetails) {
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
struct PermissionDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        PermissionDetailsView(PermissionDetailsPreview())
            .preferredColorScheme(.light)
        PermissionDetailsView(PermissionDetailsPreview())
            .preferredColorScheme(.dark)
    }
    
    class PermissionDetailsPreview: PermissionDetails {
        func onBackPressed_() {}
    }
}
#endif
