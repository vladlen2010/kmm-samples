import SwiftUI
import shared

struct PermissionListView: View {
    @ObservedObject
    private var models: ObservableValue<PermissionListModel>
    
    private let component: PermissionList
    
    init(_ component: PermissionList) {
        self.models = ObservableValue(component.model)
        self.component = component
    }
    
    
    var body: some View {
        
        let model = self.models.value
        
        List(model.items, id: \.self) { permission in
            Button(action: {
                component.onPermissionClicked(permission: permission)
            }, label: {
                Text(permission.title)
            })
        }
        .listStyle(.inset)
        
        
    }
}

#if DEBUG
struct PermissionList_Previews: PreviewProvider {
    static var previews: some View {
        PermissionListView(PermissionListPreview())
            .preferredColorScheme(.light)
        PermissionListView(PermissionListPreview())
            .preferredColorScheme(.dark)
    }
    
    class PermissionListPreview : PermissionList {
        var model: Value<PermissionListModel> = mutableValue(
            PermissionListModel(
                items: [
                    Permission(id: "1", title: "Permission 1", type: PermissionType.web),
                    Permission(id: "2", title: "Permission 2", type: PermissionType.web),
                    Permission(id: "3", title: "Permission 3", type: PermissionType.web),
                    Permission(id: "4", title: "Permission 4", type: PermissionType.web),
                    Permission(id: "5", title: "Permission 5", type: PermissionType.web)
                ]
            )
        )
        
        func onPermissionClicked(permission: Permission) {}
    }
}
#endif
