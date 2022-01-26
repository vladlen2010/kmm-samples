import shared

func simpleRouterState<T: AnyObject>(_ child: T) -> Value<RouterState<AnyObject, T>> {
    return mutableValue(
        RouterState(
            configuration: "config" as AnyObject,
            instance: child
        )
    )
}
