app();

function app(){

    let totalUsersCount = document.getElementById("total-users-count");
    let totalSubscriptionsCount = document.getElementById("total-subscriptions-count");
    let itemsPerUser = document.getElementById("average-items-per-user");

    fetch("http://localhost:8080/users/dataForAdmin")
        .then(data => data.json())
        .then(info => {

            totalUsersCount.textContent = totalUsersCount.textContent + " " + info.usersCount;
            totalSubscriptionsCount.textContent = totalSubscriptionsCount.textContent + " " + info.itemsInSubscriptions;
            itemsPerUser.textContent = itemsPerUser.textContent + " " + info.itemsPerUser;
        })
}