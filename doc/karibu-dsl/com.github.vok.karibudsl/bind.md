[com.github.vok.karibudsl](index.md) / [bind](.)

# bind

`fun <BEAN, FIELDVALUE> <ERROR CLASS><FIELDVALUE>.bind(binder: <ERROR CLASS><BEAN>): <ERROR CLASS><BEAN, FIELDVALUE>`

Allows you to bind the component directly in the component's definition. E.g.

```
textField("Name:") {
  bind(binder).trimmingConverter().bind(Person::name)
}
```

`fun <BEAN, FIELDVALUE> <ERROR CLASS><BEAN, FIELDVALUE>.bind(prop: KMutableProperty1<BEAN, out FIELDVALUE?>): <ERROR CLASS><BEAN, FIELDVALUE?>`

A type-safe binding which binds only to a property of given type, found on given bean.

### Parameters

`prop` - the bean property