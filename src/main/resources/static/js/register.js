async function registerUser () {
let data = {};
    data.name = document.getElementById('name').value;
    data.phone = document.getElementById('phone').value;
    data.email = document.getElementById('email').value;
    data.password = document.getElementById('password').value;
    data.address = document.getElementById('address').value;
    console.log("Procesando . . . ");
    const request = await fetch('api/registerUser', {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    console.log(request);
    console.log("Exito al procesasr!");
    alert("Se ha registrado el usuario");
    document.getElementById('name').value = "";
    document.getElementById('phone').value = "";
    document.getElementById('email').value = "";
    document.getElementById('password').value = "";
    document.getElementById('repeatPassword').value = "";
    document.getElementById('address').value = "";
    window.location.href("login.html");




}