const editUserForm = document.getElementById("edit-user-form")

async function editModal(id) {
    fetch('http://localhost:8088/api/admin/users/' + id
        , {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }
    ).then(res => {
        res.json()
            .then(async user => {
                console.log(user);
                document.getElementById('idEdit').value = user.id;
                document.getElementById('firstNameEdit').value = user.name;
                document.getElementById('lastNameEdit').value = user.lastName;
                document.getElementById('ageEdit').value = user.age;
                document.getElementById('emailEdit').value = user.email;
                document.getElementById('passwordEdit').value = user.password;

                // await fetch("http://localhost:8088/api/admin/roles")
                //     .then(res => res.json())
                //     .then(roleList => {
                //         roleList.forEach(role => {
                //             let selectedRole = false;
                //             for (let i = 0; i < user.roleList.length; i++) {
                //                 if (user.roleList[i].role === role.role) {
                //                     selectedRole = true;
                //                     break;
                //                 }
                //             }
                //             let el = document.createElement("option");
                //             el.text = role.role.substring(5);
                //             el.value = role.id;
                //             if (selectedRole) el.selected = true;
                //             $('#rolesEditUser')[0].appendChild(el);
                //         })
                //     })

                // if (user.roles.length === 2) {
                //     document.getElementById('adminFlag').setAttribute('selected', 'true');
                //     document.getElementById('userFlag').setAttribute('selected', 'true');
                // } else if (user.roles.length === 1 && (user.roles[0].id === 1)) {
                //     document.getElementById('adminFlag').setAttribute('selected', 'true');
                // } else if (user.roles.length === 1 && (user.roles[0].id === 2)) {
                //     document.getElementById('userFlag').setAttribute('selected', 'true');
                // }

            })
    })
}

// function editUser() {

editUserForm.addEventListener('submit', (e)=>{
    e.preventDefault();

    // const editForm = document.forms["edit-user-form"];

    let idValue = document.getElementById("idEdit").value;
    let nameValue = document.getElementById('firstNameEdit').value;
    let lastNameValue = document.getElementById('lastNameEdit').value;
    let ageValue = document.getElementById('ageEdit').value;
    let emailValue = document.getElementById('emailEdit').value;
    let passwordValue = document.getElementById('passwordEdit').value;

    // let editUserRoles = [];
    // if (editForm.roles !== undefined) {
    //     for (let i = 0; i < editForm.roles.options.length; i++) {
    //         if (editForm.roles.options[i].selected) editUserRoles.push({
    //             id: editForm.roles.options[i].value,
    //             role: "ROLE_" + editForm.roles.options[i].text
    //         })
    //     }
    // }


    // let rolesValue = getRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value));


    fetch('http://localhost:8088/api/admin/users', {
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
            roles: editUserRoles,
        })
    })
        .then(user => {
            // const usersArr = [];
            // usersArr.push(user);
            showAllUsers(user);
        })
        .then(() => {
            document.getElementById("nav-admin-tab").click();})

        // .then(() => {
            // document.getElementById("nav-admin-tab").click();
            // showAllUsers();
            // document.getElementById("closeEditModal").click();
        // })
})
