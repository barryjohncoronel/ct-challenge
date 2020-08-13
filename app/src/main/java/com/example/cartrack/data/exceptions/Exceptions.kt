package com.example.cartrack.data.exceptions

class GenericException : Throwable("Something went wrong!")

class NoUserCredentialsFoundException : Throwable("No users found.")

class UserCredentialsAlreadyExistsException : Throwable("Username already exists.")

class InvalidUserCredentialsException : Throwable("Invalid credentials.")

class NoInternetException: Throwable("No internet connection.")