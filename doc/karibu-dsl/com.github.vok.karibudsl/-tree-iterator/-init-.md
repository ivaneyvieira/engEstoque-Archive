[com.github.vok.karibudsl](../index.md) / [TreeIterator](index.md) / [&lt;init&gt;](.)

# &lt;init&gt;

`TreeIterator(root: T, children: (T) -> Iterable<T>)`

Given a tree root and a closure which computes children, iterates recursively over a tree of objects.
Iterator works breadth-first: initially the root itself is offered, then its children, then the child's children etc.

