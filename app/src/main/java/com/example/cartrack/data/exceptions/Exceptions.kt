package com.example.cartrack.data.exceptions

class NoUserFoundException : Throwable("No users found.")

class UserAlreadyExistsException : Throwable("Username already exists.")

class InvalidUserException : Throwable("Invalid credentials.")

class NoInternetException: Throwable("No internet connection.")