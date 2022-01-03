import SwiftUI
import shared

struct ApplicationListView: View {
    
    @ObservedObject
    private var models: ObservableValue<ApplicationListModel>
    
    private let component: ApplicationList
    
    init(_ component: ApplicationList) {
        self.models = ObservableValue(component.model)
        self.component = component
    }
    
    var body: some View {
        
        let model = self.models.value
        
        List(model.applications, id: \.self) { application in
            
            Button(action: {
                component.onItemClicked(applicationId: application.id)
            }, label: {
                Text(application.title)
            })
        }
        .listStyle(.inset)
        
    }
}

#if DEBUG
struct ApplicationListView_Previews: PreviewProvider {
    static var previews: some View {
        ApplicationListView(ApplicationListPreview())
            .preferredColorScheme(.light)
        ApplicationListView(ApplicationListPreview())
            .preferredColorScheme(.dark)
    }
    
    class ApplicationListPreview : ApplicationList {
        
        let model: Value<ApplicationListModel> = mutableValue(
            
            ApplicationListModel(
                applications: [
                    Application(
                        id: "1",
                        title: "Application 1"
                    ),
                    Application(
                        id: "2",
                        title: "Application 2"
                    ),
                    Application(
                        id: "3",
                        title: "Application 3"
                    )
                ]
            )
        )
        
        func onItemClicked(applicationId: String) {}
    }
}
#endif
