package com.teckathon.missingpeopleapp.util

import java.io.IOException

/**
 * A class for custom Api Exceptions
 */
class ApiException(message: String) :  IOException(message)

/**
 * A class for custom NoInternet Exceptions
 */
class NoInternetException(message: String): IOException(message)