import SwiftUI
import shared

struct PostDetailsContentView: View {
    
    let title: String
    let bodyText: String
    
    var body: some View {
        VStack {
            Text(title)
            Text(bodyText)
        }
    }
}

struct PostDetailsContentView_Previews: PreviewProvider {
    static var previews: some View {
        PostDetailsContentView(
            title: "title",
            bodyText: "body"
        )
    }
}
