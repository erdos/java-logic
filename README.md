# java-logic

Constructive logic framework in Java

This is a naive experiment to prove theorems using generics in the java type system.

# General Idea

Generic types in Java are used to give type parameters to types. For example, the type of a list of integers is written as `List<Integer>`. A map of string keys and integer values is written as `Map<String, Integer>`. The parameters are also types, so nesting generic types is also possible: `List<List<String>>` is a list of lists of strings.

Now consider the following idea: If a type can represent a logic formula, then acquiring (instantializing) an instance of a type is equivalent to constructing a proof of a logic formula. An object instance represents a proof. Note, that a type may have many different instances just like a theorem may have many different proofs.

## Building blocks

1. Logical Conjuction is just an immutable object with the type of `And<A,B>`. It contains with two values received on creation time.
2. Implication is an interface of type `Imply<A,B>` meaning `A` implies `B`.
3. Disjunction is an interface that accepts two implications and one of them is called. That is `Or<A,B>` receives `Imply<A,C>` and `Imply<B,C`> and calls one of them to return `C`. The visitor called depends on which side of the disjuction is true.
4. Negation (`Not<A>`) and Equivalence (`Iff<A,B>`) synonyms so they are defined as abstract classes.
5. Logical false is represented by the empty set. The `Bottom` enum can not be instantialized so returning an instance of it is a contradiction.

## Rules

You can prove a logical theorem by implementing its type. 

However, you need to be aware of some programming rules.

1. Do not use `null` values. Null values match every single type (even `Bottom b = null` compiles).
2. Do not throw exceptions.
3. Do not create infinite loops.

# License

Copyright 2017 Janos Erdos

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
