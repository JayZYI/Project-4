<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>


    <link rel='stylesheet'  href='css/main.css'>

    <!-- <script src="js/db.js"></script> -->
    <script src="js/main.js"></script>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

</head>
<body>
    
    <div class="container">
        <input type="checkbox" id="check">

        <div class="login form">
            <h2>ShoeStore</h2>
            <form action="#">
                <input type="email" id="email" placeholder="Enter Your Email">
                <input type="password" id="password" placeholder="Enter Your Password">
                <a href="#">Forgot Password?</a>
                <input type="button" class="button" value="Login" onclick="signIn()">
            </form>
            <div class="signup">
                <span class="signup">Don't have an account?
                    <label for="check">Signup</label>
                </span>
            </div>
        </div>


        <div class="registration form">
            <h2>ShoeStore</h2>
            <form action="#">
                <input type="email" id="email" placeholder="Enter Your Email">
                <input type="password" id="passwrod" placeholder="Create a password">
                <input type="button" class="button" value="Signup" onclick="signUp()">
            </form>
            <div class="signup">
                <span class="signup">Already have an account?
                    <label for="check">Login</label>
                </span>
            </div>
        </div>
        

        
        

        
    </div>

















    <!-- Scripts -->
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.15.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.15.0/firebase-database.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.15.0/firebase-firestore.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.15.0/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.14.4/firebase-auth.js"></script>


<!-- Config -->
<script>
    var firebaseConfig = {
    apiKey: "AIzaSyB52iEB7SGWkLi4m_f3crNHXL-24TlOvUc",
    authDomain: "shoestore-e4189.firebaseapp.com",
    databaseURL: "https://shoestore-e4189-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "shoestore-e4189",
    storageBucket: "shoestore-e4189.appspot.com",
    messagingSenderId: "962770911192",
    appId: "1:962770911192:web:7c710f69c1b9442719fcad",
    measurementId: "G-5WMZ70YZWE"
    };
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    firebase.analytics();


    var databaseRef = firebase.database().ref('test/');
	console.log(databaseRef)
</script>

<script>
    
    function signIn() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    firebase.auth().signInWithEmailAndPassword(email, password)
        .then(function () {
            window.location.href = "main.jsp";
        })
        .catch(function (error) {
            var errorCode = error.code;
            var errorMessage = error.message;
            
            if (errorCode === "auth/user-not-found" || errorCode === "auth/wrong-password") {
                alert("Invalid email or password. Please try again.");
            } else {
                alert(errorMessage);
            }
        });
    }

    function signUp() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    firebase.auth().createUserWithEmailAndPassword(email, password)
        .then(function () {
            alert("Registration successful! You can now login with your new account.");
        })
        .catch(function (error) {
            var errorCode = error.code;
            var errorMessage = error.message;
            
            alert(errorMessage);
        });
    }



</script>
</body>
</html>