import SwiftUI

struct ErrorView: View {
    
    let text: String
    let onButtonClicked: () -> ()
    let actionTitle: String? = nil
    
    var body: some View {
        ZStack {
            VStack(alignment: .center) {
                Text(text)
                Button(action: {
                    onButtonClicked()
                }) {
                    Text(actionTitle ?? "Retry")
                }
                
            }
        }
    }
}

struct ErrorView_Previews: PreviewProvider {
    static var previews: some View {
        ErrorView(
            text: "Error",
            onButtonClicked: {}
        )
    }
}
