function editModal(id) {
    fetch('http://localhost:8088/api/admin/users/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json()
            .then(user => {
                document.getElementById('idEdit').value = user.id;
                document.getElementById('firstNameEdit').value = user.name;
                document.getElementById('lastNameEdit').value = user.lastName;
                document.getElementById('ageEdit').value = user.age;
                document.getElementById('emailEdit').value = user.email;
                document.getElementById('password').value = user.password;
                // getRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value))
            })
    })
}

function editUser() {

    let idValue = document.getElementById("idEdit").value;
    let nameValue = document.getElementById('firstNameEdit').value;
    let lastNameValue = document.getElementById('lastNameEdit').value;
    let ageValue = document.getElementById('ageEdit').value;
    let emailValue = document.getElementById('emailEdit').value;
    let passwordValue = document.getElementById('password').value;
    let roles = getRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value));


    fetch('http://localhost:8088/api/admin/users/' + idValue, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            id: idValue,
            name: nameValue,
            lastName: lastNameValue,
            age: ageValue,
            email: emailValue,
            password: passwordValue,
            roles: roles,
        })
    })
        .then(() => {
            document.getElementById("nav-admin-tab").click();
            showAllUsers();
            document.getElementById("closeEditModal").click();
        })
}

function getRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({"id": 1});
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}