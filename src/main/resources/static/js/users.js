$(document).ready(() => {
    getAllUsers();
});

function getAllUsers() {
    $.ajax({
        url: "/rest/clients/",
        method: "GET",
        success: response => {
            displayUsers(response);
        },
        error: err => {
            let responseObj = err.responseJSON;
            alert(`ERROR: " ${responseObj.message} " TIME ${responseObj.time}`);
        }
    })
}



function displayUsers(users) {
    if (users.length>0) {
        let placeholder = "";
        $.each(users, (index, user) => {
            placeholder +=
                `<tr>;
                <input class='user-id' type='hidden' value='${user.id}'>
                <td>${(index+1)}</td>;
                <td>${user.name} ${user.surname} active: ${user.statusActive}</td>
                <td>Coins:${user.coins}</td>
                <td><button class='update-user'>Update</button></td>
                <td><button class='delete-user'>Delete</button></td>
                <td><button class='addcoins-user'>Add coins</button></td>
            </tr>`;
        });
        $("#users-placeholder tbody").html(placeholder);
    } else {
        $("#users-div").html("<p>No users in the system.</p>");
    }
}

$("#add").on("click", () => {
    window.location.href = "/add";
});

$("#users-placeholder").on("click", ".delete-user", function() {
    if(confirm("Click okay to confirm user delete request")) {
        let id = this.parentNode.parentElement.querySelector(".user-id").value;
        $.ajax({
            url: `/rest/clients/delete/${id}`,
            method: "DELETE",
            success: message => {
                alert(`SUCCESS: ${message}`);
                getAllUsers();
            },
            error: err => {
                let responseObj = err.responseJSON;
                alert(`ERROR: "${responseObj.message}" TIME: ${responseObj.time}`);
            }
        });
    }
});

$("#users-placeholder").on("click", ".update-user", function() {
    let id = this.parentNode.parentElement.querySelector(".user-id").value;
    window.location.href = `/update?uid=${id}`;
});

$("#users-placeholder").on("click", ".addcoins-user", function() {
    let id = this.parentNode.parentElement.querySelector(".user-id").value;
    $.ajax({
        url: `/rest/clients/addcoins/${id}`,
        method: "PUT",
        success: message => {
            alert(`SUCCESS: ${message}`);
            getAllUsers();
        },
        error: err => {
            let responseObj = err.responseJSON;
            alert(`ERROR: "${responseObj.message}" TIME: ${responseObj.time}`);
        }
    });
});