adminViewToggle();

function adminViewToggle() {
    let orderStatsBtn = document.getElementById("order-stats-btn");
    let rightsSectionBtn = document.getElementById("rights-section-btn")

    orderStatsBtn.addEventListener("click", () => {
        orderStatsBtn.classList.add("active-btn");
        rightsSectionBtn.classList.remove("active-btn");

        document.getElementsByClassName("admin-orders-view").item(0).classList.remove("visually-hidden");
        document.getElementsByClassName("admin-rights-modifier").item(0).classList.add("visually-hidden");
    });

    rightsSectionBtn.addEventListener("click", () => {
        rightsSectionBtn.classList.add("active-btn");
        orderStatsBtn.classList.remove("active-btn");

        document.getElementsByClassName("admin-rights-modifier").item(0).classList.remove("visually-hidden");
        document.getElementsByClassName("admin-orders-view").item(0).classList.add("visually-hidden");
    });
}