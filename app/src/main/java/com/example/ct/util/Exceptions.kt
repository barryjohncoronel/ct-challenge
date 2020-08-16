package com.example.ct.util

import com.example.ct.util.AppConstants.INVALID_CREDENTIALS
import com.example.ct.util.AppConstants.NO_INTERNET_CONNECTION
import com.example.ct.util.AppConstants.NO_USERS_FOUND
import com.example.ct.util.AppConstants.USER_ALREADY_EXISTS

class GenericException : Throwable("Something went wrong!")

class NoUserCredentialsFoundException : Throwable(NO_USERS_FOUND)

class UserCredentialsAlreadyExistsException : Throwable(USER_ALREADY_EXISTS)

class InvalidUserCredentialsException : Throwable(INVALID_CREDENTIALS)

class NoInternetException: Throwable(NO_INTERNET_CONNECTION)