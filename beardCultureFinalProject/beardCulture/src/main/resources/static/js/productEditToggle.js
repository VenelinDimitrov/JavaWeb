toggleEdit();

function toggleEdit() {
    let editBtn = document.getElementById("edit-product-btn");
    let saveChangesBtn = document.getElementById("update-product-btn");

    editBtn.addEventListener("click", () => {

        document.getElementById("product-details-section")
            .classList.add("visually-hidden");
        document.getElementById("product-edit-section").classList.remove("visually-hidden");
    })

    saveChangesBtn.addEventListener("click", () => {

        document.getElementsByClassName("product-details").item(0)
            .classList.remove("visually-hidden");
        document.getElementById("product-edit").classList.add("visually-hidden");
    })
}