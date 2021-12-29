package com.example.dubizzletest.utils

typealias TestParameters = List<Array<*>>

/** Converts pairs to collection of arrays suitable for parameterized tests. */
fun parametersOf(vararg parameters: Pair<*, *>): TestParameters =
    parameters.map { it.toList().toTypedArray() }
