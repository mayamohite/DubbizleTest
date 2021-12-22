package com.example.dubizzletest.domain.common

/***
 * Base mapper class @see [DataToDomainMapper] is used to convert retrofit response models to UI models.
 */
abstract class DataToDomainMapper <in I, out O> {

    abstract fun map(input: I): O
}
