const Regform = document.getElementById('RegForm')

const username = document.getElementById('username');
const password = document.getElementById('password');
const fullname = document.getElementById('fullname');
const age = document.getElementById('age');
const email = document.getElementById('email');
const address = document.getElementById('address');

Regform.addEventListener("submit", async function (e ){
    e.preventDefault()

    const usernameValue = username.value;
    const passwordValue = password.value;
    const fullnameValue = fullname.value;
    const ageValue = age.value;
    const emailValue = email.value;
    const addressValue = address.value.trim();

    console.log('1' + usernameValue + ' : ' + passwordValue + ' : ' + fullnameValue + ' : ' + ageValue + ' : ' + emailValue + ' : ' + addressValue);


    let newData;
    //fisr step of checking username and password
    const response = await fetch("http://localhost:8082/auth/Registration", {
        method: "POST", // or 'PUT'
        headers: new Headers({"content-type": "application/json"}),
        body: JSON.stringify({
            username: usernameValue,
            password: passwordValue
        }),
    }).then(response => {
        return response.json()
    }).then(data => newData = data)
        .catch(error => console.log(error));

    checkUsernameAndPassword(JSON.stringify(newData), usernameValue, passwordValue,fullnameValue, ageValue,emailValue,addressValue)
});



function checkUsernameAndPassword(data , usernameValue, passwordValue,fullnameValue, ageValue, emailValue, addressValue){

    const userData = JSON.parse(data);
    console.log('2' + usernameValue + ' : ' + passwordValue + ' : ' + fullnameValue + ' : ' + ageValue + ' : ' + emailValue + ' : ' + addressValue);
    console.log(userData);
    const Status = userData.Status;
    if(Status === "Access"){
        setSuccess(username);
        setSuccess(password);
        CreatePerson(usernameValue, passwordValue, fullnameValue, ageValue, emailValue, addressValue);
    }
    else{
        const Error = userData.Error;
        setError(username,Error);
    }
}

// async function regUser( usernameValue, passwordValue, fullnameValue, ageValue, emailValue, addressValue){
//
//     console.log('3' + usernameValue + ' : ' + passwordValue + ' : ' + fullnameValue + ' : ' + ageValue + ' : ' + emailValue + ' : ' + addressValue);
//
//     let newData;
//     const response = await fetch("http://localhost:8082/auth/Registration/User", {
//         method: "POST", // or 'PUT'
//         headers: new Headers({"content-type": "application/json"}),
//         body: JSON.stringify({
//             username: usernameValue,
//             password: passwordValue
//         }),
//     }).then(response => {
//         return response.json()
//     }).then(user => newData = user)
//         .catch(error => console.log(error));
//
//     CreatePerson(JSON.stringify(newData), fullnameValue, ageValue, emailValue, addressValue)
// }




async function CreatePerson(usernameValue , passwordValue ,fullnameValue, ageValue, emailValue, addressValue){

    const currentTime = new Date();

    console.log('4' + ' : ' + fullnameValue + ' : ' + ageValue + ' : ' + emailValue + ' : ' + addressValue);

    let newData;
    const response = await fetch("http://localhost:8082/auth/Registration/person", {
        method: "POST", // or 'PUT'
        headers: new Headers({"content-type": "application/json"}),
        body: JSON.stringify({
            name: fullnameValue,
            age: ageValue,
            email: emailValue,
            address: addressValue,
            userData:{
                username: usernameValue,
                password: passwordValue,
                role: "Consumer",
                timestamp: currentTime
            }
        }),
    }).then(response => {
        return response.json()
    })
        .then(data => newData = data)
        .catch(error => console.log(error));

    RegPerson(JSON.stringify(newData), emailValue);
}

async function RegPerson(data, emailValue) {
    const jsonData = JSON.parse(data);
    console.log(data);
    const Status = jsonData.Status;

    console.log('5');
    console.log(Status);

    if(Status === "Created"){
        setSuccess(fullname);
        setSuccess(age);
        setSuccess(email);
        setSuccess(address);
        location.replace("http://localhost:63343/apiProject/index.html?_ijt=eep5bcj4ldljibfjcduenfol78&_ij_reload=RELOAD_ON_SAVE")
        console.log("Go to Main Page!")
    }
    else{
        const fullnameError = data.name;
        const ageError = data.age;
        const addressError = data.address;

        if(fullnameError === "Name cannot be less than 1 or more than 100"){
            setError(fullname, fullnameError);
        }else if(ageError === "Age cannot be less than 1 or more than 100"){
            setError(age, ageError);
        }else if (!isValidEmail(emailValue)) {
            setError(email, 'Provide a valid email address');
        } else if(addressError === ""){
            setError(address, 'Address is required')
        }else {
            console.log("something went wrong :( possibly user is empty");
        }
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

const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}
