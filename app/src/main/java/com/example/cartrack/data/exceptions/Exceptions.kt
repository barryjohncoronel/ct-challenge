package com.example.cartrack.data.exceptions

import com.example.cartrack.util.AppConstants.INVALID_CREDENTIALS
import com.example.cartrack.util.AppConstants.NO_INTERNET_CONNECTION
import com.example.cartrack.util.AppConstants.NO_USERS_FOUND
import com.example.cartrack.util.AppConstants.USER_ALREADY_EXISTS

class GenericException : Throwable("Something went wrong!")

class NoUserCredentialsFoundException : Throwable(NO_USERS_FOUND)

class UserCredentialsAlreadyExistsException : Throwable(USER_ALREADY_EXISTS)

class InvalidUserCredentialsException : Throwable(INVALID_CREDENTIALS)

class NoInternetException: Throwable(NO_INTERNET_CONNECTION)