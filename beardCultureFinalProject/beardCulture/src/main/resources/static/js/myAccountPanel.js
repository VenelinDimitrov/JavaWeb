myAccountToggle();

function myAccountToggle() {
    let mySubscriptionBoxBtn = document.getElementById("my-box-btn");
    let myUserDetailsBtn = document.getElementById("my-user-details-btn");

    mySubscriptionBoxBtn.addEventListener("click", () => {
        mySubscriptionBoxBtn.classList.add("active-btn");
        myUserDetailsBtn.classList.remove("active-btn");

        document.getElementsByClassName("subscription-items").item(0)
            .classList.remove("visually-hidden");
        document.getElementById("my-user-details-form").classList.add("visually-hidden");
    });

    myUserDetailsBtn.addEventListener("click", () => {
        myUserDetailsBtn.classList.add("active-btn");
        mySubscriptionBoxBtn.classList.remove("active-btn");

        document.getElementsByClassName("subscription-items").item(0)
            .classList.add("visually-hidden");
        document.getElementById("my-user-details-form").classList.remove("visually-hidden");
    })
}