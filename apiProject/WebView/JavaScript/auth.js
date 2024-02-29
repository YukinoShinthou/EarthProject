const form = document.getElementById('LoginForm');
const username = document.getElementById('username');
const password = document.getElementById('password');

form.addEventListener("submit", async function (e) {
    e.preventDefault()
    const usernameValue = username.value;
    const passwordValue = password.value;


    const response = await fetch("http://localhost:8082/auth/Login", {
        method: "POST", // or 'PUT'
        headers: new Headers({"content-type": "application/json"}),
        body: JSON.stringify({
            username: usernameValue,
            password: passwordValue
        }),
    }).then(response => {
        return response.json()
    }).then(data => checkStatus(JSON.stringify(data), usernameValue, passwordValue))
        .catch(error => console.log(error));

});

function checkStatus(data, usernameValue, passwordValue){
    const person = JSON.parse(data);
    const Status = person.Status;

    if(Status === "Access"){
        checkInputs(person);
        console.log(person);
        console.log("Go to Main Page!");

    }else if(Status === "Reject"){
        checkInputs(person);
    }else {
        console.log("Something Went wrong :(");
    }
}
const setError = (element, message)=> {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}
const setSuccess = element =>{
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error')
}

function checkInputs(person){

    const ErrorMessage = person.Error;

    if(ErrorMessage === "Incorrect Username"){
        setError(username, ErrorMessage);
    }
    else if(ErrorMessage === "Incorrect Password"){

        //Password is not correct but the username is correct
        setSuccess(username);
        //password error
        setError(password,ErrorMessage);

        location.replace("http://localhost:63343/apiProject/index.html?_ijt=eep5bcj4ldljibfjcduenfol78&_ij_reload=RELOAD_ON_SAVE");

    }else{
        setSuccess(username);
        setSuccess(password);
    }
}


