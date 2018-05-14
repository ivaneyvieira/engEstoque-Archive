[com.github.vok.karibudsl](../index.md) / [TreeIterator](.)

# TreeIterator

`class TreeIterator<out T> : Iterator<T>`

Given a tree root and a closure which computes children, iterates recursively over a tree of objects.
Iterator works breadth-first: initially the root itself is offered, then its children, then the child's children etc.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TreeIterator(root: T, children: (T) -> Iterable<T>)`<br>Given a tree root and a closure which computes children, iterates recursively over a tree of objects.
Iterator works breadth-first: initially the root itself is offered, then its children, then the child's children etc. |

### Functions

| Name | Summary |
|---|---|
| [hasNext](has-next.md) | `fun hasNext(): Boolean` |
| [next](next.md) | `fun next(): T` |
