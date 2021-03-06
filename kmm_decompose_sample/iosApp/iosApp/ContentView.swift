import SwiftUI
import shared

struct ContentView: View {
    @State
    private var componentHolder = ComponentHolder(factory: RootComponent.init)
    
    var body: some View {
        RootView(componentHolder.component)
            .onAppear { LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle) }
    }
}

#if DEBUG
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
            .preferredColorScheme(.light)
        ContentView()
            .preferredColorScheme(.dark)
	}
}
#endif
