# Servlet Login Page <img src="https://img.icons8.com/color/48/000000/lock--v2.png"/>

This program containes simple login page and registration page connected with Java servlet and a local MySQL DB for storing the IDs and Passwords during registration. These registered users can later login through the given credentials.

All passwords during registration are checked using regex. The regex pattern verifies that the password must have : 

- At least one upper case alphabet
- At least one lower case alphabet
- At least one digit
- At least one special character
- Minimum eight in length

The passwords are then converted to SHA256 before insertion in the database.

<img src="https://raw.githubusercontent.com/sirajkhan831/laboratory/master/out/production/laboratory/com/company/example/Screenshot%202021-11-27%20175932.png">
