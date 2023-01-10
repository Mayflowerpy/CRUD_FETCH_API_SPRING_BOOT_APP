const allUsersTable = document.getElementById("all-users-table");

function showAllUsers() {
    fetch("http://localhost:8088/api/admin/users")
        .then((res) => res.json())
        .then(
            (users) => {
                if (users.length > 0) {
                    let temp = "";

                    users.forEach((user) => {
                        temp += `<tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.roles.map(role => " " + role.name.substring(5))}</td> 
                        <td>
                             <button class="btn btn-info" type="button"
                             data-toggle="modal" data-target="#modalEdit"
                             onclick="editModal(${user.id})">Edit</button></td>
                             <td>
                             <button class="btn btn-danger" type="button"
                             data-toggle="modal" data-target="#modalDelete"
                             onclick="deleteModal(${user.id})">Delete</button></td>
                        </tr>`;
                    })
                    allUsersTable.innerHTML = temp;
                }
            }
        )
}

showAllUsers()