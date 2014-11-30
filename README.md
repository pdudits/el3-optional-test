Using EL3 Lambdas with SE8 Lambdas
==================================

This is an attempt to use Java SE 8 collection constructs with EL 3.0 (tested in WildFly 8.2).

So far it hasn't been very successful, because EL RI only uses [it's own mini-implementation of streams]
(https://svn.java.net/svn/uel~svn/trunk/impl/src/main/java/com/sun/el/stream/), and lambda expressions cannot be
passed to `java.util` classes
