I used list as datastructure for storing elements. Add type parameter for storing
different elements types in our stack. For prevent concurrent access to list i wrap all
methods that are working with underlying list. For test correctness i wrote
test which push and pop same number of elements, wrap operations with the stack into Future instance
and run it. We should receive that the number of elements in stack will be the same as it was before
starting futures, because futures contains equal numbers of pop and push operations.

Another one test only push elements into the stack. All push operations was wrapped into Future. 
After all futures will completed we should receive the stack with size "all running futures * loop iterations"

You could easily check it correctness - just remove `this.synchronized` from code, and you will
see that some tests will fall due to concurrent access to stack.
