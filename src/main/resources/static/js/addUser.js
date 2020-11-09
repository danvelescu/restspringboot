
$("#add-user-submit").on("click", () => {
    if (validInput() === true) {
        $.ajax({
            method: "POST",
            url: "rest/clients/add",
            data: JSON.stringify(userObject()),
            contentType: "application/json",
            success: response => {
                alert(`SUCCESS: ${response}`);
                window.location.href = "/";
            },
            error: err => {
                let errorObj = err.responseJSON;
                alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
            }
        });
    } else {
        alert("I am not making the call because of invalid input");
    }
});

const userObject = () => {
    return {
        name: $("#name").val(),
        surname: $("#surname").val(),
        statusActive : true,
        coins : 0
    };
};

const validInput = () => {
    return true;
};