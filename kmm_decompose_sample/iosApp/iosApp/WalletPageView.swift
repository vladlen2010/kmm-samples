import SwiftUI
import shared

struct WalletPageView: View {
    
    @ObservedObject
    private var model: ObservableValue<WalletPageModel>
    
    private var component: WalletPage
    
    init(_ component: WalletPage) {
        self.model = ObservableValue(component.model)
        self.component = component
    }
    
    var body: some View {
        
        let model = self.model.value
        
        VStack {
            Text("Balance: \(model.amount)")
        }
        .frame(
            maxWidth:.infinity,
            maxHeight: .infinity,
            alignment: .top
        )
        .padding()
        
    }
}

#if DEBUG
struct WalletPageView_Previews: PreviewProvider {
    static var previews: some View {
        WalletPageView(WalletPagePreview())
            .preferredColorScheme(.light)
        WalletPageView(WalletPagePreview())
            .preferredColorScheme(.dark)
    }
    
    class WalletPagePreview : WalletPage {
        var model: Value<WalletPageModel> = mutableValue(
            WalletPageModel(amount: "1000000")
        )
    }
}
#endif
