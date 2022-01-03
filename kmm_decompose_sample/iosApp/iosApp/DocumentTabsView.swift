import SwiftUI
import shared

struct DocumentTabsView: View {
    
    @ObservedObject
    private var model: ObservableValue<DocumentTabsModel>
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, DocumentTabsChild>>
    
    private let component: DocumentTabs
    
    init(_ component: DocumentTabs) {
        self.model = ObservableValue(component.model)
        self.component = component
        self.routerState = ObservableValue(component.routerState)
    }
    
    let tabNames: [String] = ["Documents", "Applications"]
    
    private enum Tab: Int {
        case DOCUMENTS
        case APPLICATIONS
    }
    
    var body: some View {
        let model = self.model.value
        let child = self.routerState.value.activeChild.instance
        
        let selectedTab = Binding<Int>(
            get: {
                switch model.selectedTab {
                case DocumentTabsTab.documentsTab:
                    return Tab.DOCUMENTS.rawValue
                case DocumentTabsTab.applicationTab:
                    return Tab.APPLICATIONS.rawValue
                default:
                    return Tab.DOCUMENTS.rawValue
                }
            },
            set: { newValue in
                switch newValue {
                case Tab.DOCUMENTS.rawValue:
                    component.onTabClick(tab: .documentsTab)
                case Tab.APPLICATIONS.rawValue:
                    component.onTabClick(tab: .applicationTab)
                default:
                    component.onTabClick(tab: .documentsTab)
                }
            }
        )
        
        VStack {
            TabRow(selectedTab: selectedTab)
            
            
            switch child {
            case let document as DocumentTabsChild.Documents:
                DocumentsPageView(document.component)
            case let application as DocumentTabsChild.Applications:
                ApplicationsPageView(application.component)
            default:
                EmptyView()
            }
            
            Button(action: {
                component.onAddDocumentClicked()
            }) {
                Text("Add Document")
                    .fontWeight(.semibold)
                    .padding()
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .foregroundColor(.white)
                    .background(Color.blue)
                    .cornerRadius(5)
            }
            .padding()
        }
    }
}

struct TabRow: View {
    
    @Binding var selectedTab: Int
    
    var body: some View {
        HStack(spacing: 12) {
            Button(action: {
                self.selectedTab = 0
                
                //
            }) {
                Text("Documents")
                    .frame(maxWidth: .infinity)
                    .padding(10)
                    .font(.headline)
                    .foregroundColor(self.selectedTab == 0 ? .white : .gray)
                    .background(
                        Rectangle()
                            .fill(self.selectedTab == 0 ? .green : .white)
                            .cornerRadius(5)
                    )
            }
            Button(action: {
                self.selectedTab = 1
                //
            }) {
                Text("Applications")
                    .frame(maxWidth: .infinity)
                    .padding(10)
                    .font(.headline)
                    .foregroundColor(self.selectedTab == 1 ? .white : .gray)
                    .background(
                        Rectangle()
                            .fill(self.selectedTab == 1 ? .green : .white)
                            .cornerRadius(5)
                    )
            }
        }
        .padding(.horizontal, 16)
    }
}

#if DEBUG
struct DocumentTabsView_Previews: PreviewProvider {
    static var previews: some View {
        DocumentTabsView(DocumentTabsPreview())
            .preferredColorScheme(.light)
        DocumentTabsView(DocumentTabsPreview())
            .preferredColorScheme(.dark)
    }
    
    
    class DocumentTabsPreview: DocumentTabs {
        
        var model: Value<DocumentTabsModel> = mutableValue(
            DocumentTabsModel(selectedTab: DocumentTabsTab.documentsTab)
        )
        
        var routerState: Value<RouterState<AnyObject, DocumentTabsChild>> = simpleRouterState(
            DocumentTabsChild.Documents(component: DocumentsPageView_Previews.DocumentPagePreview())
        )
        func onTabClick(tab: DocumentTabsTab) {}
        func onAddDocumentClicked() {}
    }
}
#endif
