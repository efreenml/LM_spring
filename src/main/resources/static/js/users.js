// Call the dataTables jQuery plugin
$(document).ready(function() {
    getUsers();
  $('#users').DataTable();
});

async function getUsers() {
  const request = await fetch("api/users", {
    method: "GET",
    headers: getHeaders(),
  });
  const users = await request.json();
  if (users == null) {
  return alert("token inválido");
  }
  let usersHtml = '';
    for (let user of users) {
    let aLabel = '<a href="#" onclick="deleteUser('+user.id+')" class="bt btn-danger btn-circle btn-sm" ><i class="fas fa-trash"></i></a>';
          let userHtml = `<tr>
                              <td>${user.id}</td>
                              <td>${user.name}</td>
                              <td>${user.email}</td>
                              <td>${user.phone}</td>
                              <td>${user.password}</td>
                              <td>${aLabel}</td>
                          </tr>`;

    usersHtml += userHtml;
    }
    console.log(localStorage.email);
  document.getElementById('userProfileName').outerHTML = localStorage.email;
  document.querySelector('#users tbody').outerHTML = usersHtml;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

async function deleteUser(id) {

    if (!confirm('¿desea eliminar al usuario?'))
        return;
      const request = await fetch("api/deleteUser/"+id, {
        method: "DELETE",
        headers: getHeaders(),
      });
      location.reload();
}