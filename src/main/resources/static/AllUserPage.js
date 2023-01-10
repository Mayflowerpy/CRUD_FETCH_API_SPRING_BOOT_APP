const allUsersTable = document.getElementById("all-users-table");
const adminPanel = document.getElementById("admin-panel");
const addForm = document.getElementById("add-form");
const editForm = document.getElementById("edit-form");
const deleteForm = document.getElementById("delete-form");


function showAllUsers() {
    fetch("http://localhost:8088/api/users")
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
                        <td>${roles}</td> 
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