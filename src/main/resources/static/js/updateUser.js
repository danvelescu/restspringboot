$(document).ready(() => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/rest/clients/${id}`,
        method: "GET",
        success: users => {
            fillInputs(users);
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    })
});

function fillInputs(data) {
    $("#name-update").val(data.name);
    $("#surname-update").val(data.surname);
    $("#coins-update").val(data.coins);
}

$("#update-client-submit").on("click", () => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/rest/clients/update/${id}`,
        method: "PUT",
        data: JSON.stringify(newUserObj()),
        contentType: "application/json",
        success: response => {
            alert(`SUCCESS: ${response}`);
            window.location.href = "/";
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    });
});

const newUserObj = () => {
    return {
        name: $("#name-update").val(),
        surname: $("#surname-update").val(),
        coins: $("#coin-update").val()
    };
};