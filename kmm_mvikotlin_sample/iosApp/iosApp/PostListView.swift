import SwiftUI
import shared

struct PostListView: View {
    
    @ObservedObject
    var model: ObservableValue<PostListComponentModel>
    
    let component: PostListComponent
    
    init(_ component: PostListComponent) {
        self.component = component
        self.model = ObservableValue(component.model)
    }
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}
//
//struct PostListView_Previews: PreviewProvider {
//    static var previews: some View {
//        PostListView()
//    }
//}
