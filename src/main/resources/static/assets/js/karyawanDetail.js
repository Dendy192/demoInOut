// rome(input_to, {
//   dateValidator: rome.val.afterEq(new Date()),
//   inputFormat: "DD-MMM-YYYY",
//   time: false,
// });
function removeAttributeComponent(id) {
  document.getElementById(id).removeAttribute("disabled");
}

function styleDisplay(id, display) {
  document.getElementById(id).style.display = display;
}

console.log();
const editBtn = document.getElementById("editBtn");
const cancelBtn = document.getElementById("cancelBtn");
editBtn.addEventListener("click", function () {
  console.log("masuk sini dah ");
  styleDisplay("cancelDiv", "block");
  styleDisplay("editDiv", "none");
  rome(input_to, {
    dateValidator: rome.val.afterEq(new Date()),
    inputFormat: "DD-MMM-YYYY",
    time: false,
  });
  var inputs = document.querySelectorAll("input");

  inputs.forEach(function (input) {
    input.removeAttribute("disabled");
  });
  removeAttributeComponent("formGroupDefaultSelect");
  removeAttributeComponent("insertFotoBtn");
  removeAttributeComponent("hapusFotoBtn");
  removeAttributeComponent("insertKTPBtn");
  removeAttributeComponent("hapusKTPBtn");
});
cancelBtn.addEventListener("click", function () {
  window.location.href = "karyawanDetail.html";
});
$(document).ready(function () {
  var inputs = document.querySelectorAll("input");
  document
    .getElementById("formGroupDefaultSelect")
    .setAttribute("disabled", true);
  inputs.forEach(function (input) {
    input.setAttribute("disabled", true);
  });
});
