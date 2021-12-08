app();

function app(){

    let totalUsersCount = document.getElementById("total-users-count");
    let totalSubscriptionsCount = document.getElementById("total-subscriptions-count");
    let itemsPerUser = document.getElementById("average-items-per-user");

    fetch("http://localhost:8080/users/dataForAdmin")
        .then(data => data.json())
        .then(info => {
            console.log(info);
            console.log(info.usersCount);
            console.log(info.usersCount);
            console.log(info.usersCount);

            totalUsersCount.textContent = totalUsersCount.textContent + " " + info.usersCount;
            totalSubscriptionsCount.textContent = totalSubscriptionsCount.textContent + " " + info.itemsInSubscriptions;
            itemsPerUser.textContent = itemsPerUser.textContent + " " + info.itemsPerUser;
        })
}