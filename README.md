Signals
===

Another problem we faced in Babbla was that we would need to analyze large amounts of data, and define complex interactions between them. It was fairly obvious that standard ways of handling data in Java, such as lists, would mean huge amounts of fragile code. Combined with an arbitrary number of plugins that all need to use data from each other, the software would become unmaintainable very quickly.

Functional Reactive Programming
---

Just like the latest versions of Java itself, we allowed ourselves to be inspired by functional programming languages. One way of programming that has been researched by those languages is called functional reactive programming. This means that instead of just having a single value, we look at data as a set of signals that changes over time. Since we now have a continuous signal representing the value of some data, we can also define filters to apply to that data, providing a new continuous signal. Through this very abstract definition, we can very easily model a large set of complex interactions requiring almost no programming. 

In order to make this system more concrete, we will provide a simple usage example. Let us have a truck driving on the road. We now want to display a message to the user whenever the truck driver has been driving over the speed limit for more than ten seconds. The following actions need to be performed on the source signals:
We take the “current speed” signal provided by the truck and compare it to the current speed limit (defined in some plugin through other signals).
We apply a delay filter to the comparison result, giving us the signal value ten seconds back in time.
We apply a change filter to the delayed comparison, making sure that only updates where the result is changed are retained.
We apply a predicate filter to the filtered signal, providing a discrete event type that is updated whenever we go over the speed limit. Binding a “showMessage” function to this event type will provide the desired functionality.

All of this can be done with the following line of Java code, split for clarity:

    bind(this::showMsg, 
        predicate(
        changeFilter(
        delay(
            10, 
            greater(
                aga.speed, 
                gps.speedLimit
            )
        ))));
