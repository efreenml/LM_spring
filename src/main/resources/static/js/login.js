
async function login() {
    let data = new Object();
    data.email = document.getElementById("email").value;
    data.password = document.getElementById("password").value;

    let request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    });
    const response = await request.text();
    console.log(response);
    if (response == 'not found 401') {
        alert("incorrect user");
    } else {
        window.location.href = 'users.html';
        localStorage.token = response;
        localStorage.email = data.email;
    }

}