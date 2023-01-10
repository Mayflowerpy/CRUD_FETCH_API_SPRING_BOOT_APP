const data = document.getElementById("data-user");
const url = 'http://localhost:8088/api/viewUser';
const panel = document.getElementById("user-panel");

function userAuthInfo() {
    fetch(url)
        .then((res) => res.json())
        .then((user) => {

            let temp = '';
            let roles = '';
            for (let i = 0; i < user.roles.length; i++) {
                roles += user.roles[i].name.substring(5) + ' ';
            }

            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${roles}</td> 
            </tr>`;
            data.innerHTML = temp;
            panel.innerHTML = `<h5>${user.email} with roles: ${roles}</h5>`
        });
}

userAuthInfo()