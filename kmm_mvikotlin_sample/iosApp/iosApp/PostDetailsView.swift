import SwiftUI
import shared

struct PostDetailsView: View {
    
    @ObservedObject
    var model: ObservableValue<PostDetailsComponentModel>
    
    let component: PostDetailsComponent
    
    init(_ component: PostDetailsComponent) {
        self.component = component
        self.model = ObservableValue(component.model)
    }
    
    var body: some View {
        let model = self.model.value
        
        NavigationView {
            ZStack {
                switch model {
                case let content as PostDetailsComponentModel.Content:
                    PostDetailsContentView(
                        title: content.title,
                        bodyText: content.body
                    )
                case is PostDetailsComponentModel.Loading:
                    Loader()
                case is PostDetailsComponentModel.Error:
                    ErrorView(text: "Error") {
                        component.reload()
                    }
                default:
                    fatalError()
                }
            }
            .navigationBarItems(leading: Button(action: {
                component.onCloseClick()
            }) {
                HStack {
                    Image(systemName: "chevron.left")
                    Text("Back")
                }
            })
        }
        
    }
}

struct PostDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        PostDetailsView(PostDetailPreview())
            .preferredColorScheme(.light)
        PostDetailsView(PostDetailPreview())
            .preferredColorScheme(.dark)
    }
    
    class PostDetailPreview : PostDetailsComponent {
        static let contentStub = PostDetailsComponentModel.Content(
            title: "Post #1",
            body: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
        )
        
        var model: Value<PostDetailsComponentModel> = mutableValue(contentStub)
        
        func onCloseClick() {}
        func reload() {}
    }
}
