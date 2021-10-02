CONSTRUCTOR INJECTION vs FIELD INJECTION

Testability

If we use CONSTRUCTOR INJECTION, we don't have to use
Spring annotations, i.e. we don't have to create a Spring Context at all, so we have a faster
and cleaner tests.
With FIELD INJECTION we have to create Spring Context hence it takes much longer to run the tests.

Cyclic Dependency detection

Cyclic dependencies can be easily detected and fixed with CONSTRUCTOR INJECTION.
Cyclic dependencies are not easily detected with FIELD INJECTION.

Immutability

We can have (and we should, it's best practice) to have autowired fields final with CONSTRUCTOR INJECTION.
It makes sense, since the fields won't ever change during the lifetime of an application.
It also helps to avoid programming issues, because the compiler will complain 
if we have forgotten to initialize the field.
Fields cannot be final with FIELD INJECTION.

Performance

Only part where FIELD INJECTION is better. Object are created independently
and fields are injected later using reflection. That means startup time is faster.
In CONSTRUCTOR INJECTION case means all the beans must be created in correct order
of dependencies which increase the startup time.

Clean code

CONSTRUCTOR INJECTION forces developers to keep dependencies to a minimum, 
given that they see them in the constructor. FIELD INJECTION is very easy to add,
and thus classes with 5 or more dependencies often appear. 
Yet if there are more than 3, we should start thinking about the fact 
that the architecture is not well-thought-out. 
One service should not do some much stuff.

Reflection

Spring must use reflection to inject private fields in case of FIELD INJECTION.
If we inject with CONSTRUCTOR INJECTION, we do not have any reflection.

CONSTRUCTOR INJECTION forces us follow one of SOLID principles - Single Responsibility

A class should have only ona responsibility. Constructor forces us to have 
fewer dependencies, so that means less functionality

Summary

Always use CONSTRUCTOR INJECTION.