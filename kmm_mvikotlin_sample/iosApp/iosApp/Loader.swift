import SwiftUI

struct Loader: View {
    var body: some View {
        ZStack {
            ProgressView("Loading...")
        }
    }
}

struct Loader_Previews: PreviewProvider {
    static var previews: some View {
        Loader()
    }
}
