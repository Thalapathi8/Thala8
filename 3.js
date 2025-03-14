const API_BASE_URL = "https://your-api-url.com"; // Change to actual API URL

function createUser() {
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    fetch(API_BASE_URL + "/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password })
    })
    .then(response => response.json())
    .then(data => document.getElementById("createMessage").innerText = data.message)
    .catch(error => console.error("Error:", error));
}

function getUser() {
    let userId = document.getElementById("userId").value;

    fetch(API_BASE_URL + "/users/" + userId)
        .then(response => response.json())
        .then(data => {
            document.getElementById("userDetails").innerText = `User: ${data.username}, Email: ${data.email}`;
        })
        .catch(error => console.error("Error:", error));
}
