let cerrar = document.querySelectorAll(".btn-cancelar")[0];
let abrir = document.querySelectorAll(".addition")[0];
let VentModal = document.querySelectorAll(".ventana__modal")[0];
let modalCont = document.querySelectorAll(".modal__container--add")[0];

abrir.addEventListener("click", function (e) {
    e.preventDefault();
    modalCont.style.opacity = "1";
    modalCont.style.visibility = "visible";
    VentModal.classList.toggle("ventana-close");
});

cerrar.addEventListener("click", function (e) {
    VentModal.classList.toggle("ventana-close");
    setTimeout(function () {
        modalCont.style.opacity = "0";
        modalCont.style.visibility = "hidden";
    }, 600);
});

window.addEventListener("click", function (e) {
    console.log(e.target);
    if (e.target == modalCont) {
        VentModal.classList.toggle("ventana-close");
        setTimeout(function () {
            modalCont.style.opacity = "0";
            modalCont.style.visibility = "hidden";
        }, 600);
        document.getElementById('formulario').reset();
    }
});



/*  */



let cerrardel = document.querySelectorAll(".button-cancelar")[0];
let abrirDelete = document.querySelectorAll(".deleteProduc")[0];
let VentDelete = document.querySelectorAll(".ventana_modal--delete")[0];
let modalContDele = document.querySelectorAll(".container_modal--delete")[0];

abrirDelete.addEventListener("click", function (e) {
    e.preventDefault();
    modalContDele.style.opacity = "1";
    modalContDele.style.visibility = "visible";
    VentDelete.classList.toggle("ventana-close-delete");
});

cerrardel.addEventListener("click", function (e) {
    VentDelete.classList.toggle("ventana-close-delete");
    setTimeout(function () {
        modalContDele.style.opacity = "0";
        modalContDele.style.visibility = "hidden";
    }, 600);
});

window.addEventListener("click", function (e) {
    console.log(e.target);
    if (e.target == modalContDele) {
        VentDelete.classList.toggle("ventana-close-delete");
        setTimeout(function () {
            modalContDele.style.opacity = "0";
            modalContDele.style.visibility = "hidden";
        }, 600);
    }
});


/*  */

let cerrarEdit = document.querySelectorAll(".btn-cancelar-edit")[0];
let abrirEdit = document.querySelectorAll(".editproduc")[0];
let VentEdit = document.querySelectorAll(".ventana_modal--edit")[0];
let modalContEdit = document.querySelectorAll(".container-modal--edit")[0];

abrirEdit.addEventListener("click", function (e) {
    e.preventDefault();
    modalContEdit.style.opacity = "1";
    modalContEdit.style.visibility = "visible";
    VentEdit.classList.toggle("ventana-close-edit");
});

cerrarEdit.addEventListener("click", function (e) {
    VentEdit.classList.toggle("ventana-close-edit");
    setTimeout(function () {
        modalContEdit.style.opacity = "0";
        modalContEdit.style.visibility = "hidden";
    }, 600);
});

window.addEventListener("click", function (e) {
    console.log(e.target);
    if (e.target == modalContEdit) {
        VentEdit.classList.toggle("ventana-close-edit");
        setTimeout(function () {
            modalContEdit.style.opacity = "0";
            modalContEdit.style.visibility = "hidden";
        }, 600);
    }
});

