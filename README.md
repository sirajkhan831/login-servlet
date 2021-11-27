# Servlet Login Page <img src="https://img.icons8.com/color/48/000000/lock--v2.png"/>

This program containes simple login and registration HTML page connected with Java servlet which store the UserID and password of all the registered user on local SQL DB.

All password are checked using regex to check weather the passwords are correct or not. The regex verifies that the password must have : 

- At least one upper case alphabet
- At least one lower case alphabet
- At least one digit
- At least one special character
- Minimum eight in length

These strong passwords are first converted to SHA256 before insertion in the database.

<img src="https://raw.githubusercontent.com/sirajkhan831/laboratory/master/out/production/laboratory/com/company/example/Screenshot%202021-11-27%20175932.png">
