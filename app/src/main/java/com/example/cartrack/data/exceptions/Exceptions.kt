package com.example.cartrack.data.exceptions

class GenericException : Throwable("Something went wrong!")

class NoUserFoundException : Throwable("No users found.")

class UserAlreadyExistsException : Throwable("Username already exists.")

class InvalidUserException : Throwable("Invalid credentials.")

class NoInternetException: Throwable("No internet connection.")