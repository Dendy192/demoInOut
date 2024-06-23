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
function submitForm(){
  document.getElementById("karyawanForm").submit();
}

const editBtn = document.getElementById("editBtn");
const cancelBtn = document.getElementById("cancelBtn");
editBtn.addEventListener("click", function () {
  console.log("masuk sini dah ");
  styleDisplay("cancelDiv", "block");
  styleDisplay("editDiv", "none");
  styleDisplay("submitDiv", "block");
  rome(input_to, {
    dateValidator: rome.val.afterEq(new Date()),
    inputFormat: "DD MMM YYYY",
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
  let currentUrl = window.location.href;
  let url = new URL(currentUrl);
  let params = new URLSearchParams(url.search);
  let id = params.get('id');
  let urlBack = karyawanDetail+"?id="+id;

  window.location.href = urlBack;
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
$('#hapusFotoBtn').click(function() {
  $('#karyawanFoto').attr('src', ''); // Clears the src attribute
  $('#fotoStatus').val("delete")

});
$('#insertKTPBtn').click(function() {
  $('#ktpFotoInput').click();

});
$('#insertFotoBtn').click(function() {
  $('#inputFoto').click();

});
$('#ktpFotoInput').change(function() {
  var input = this;
  $('#fotoKtpStatus').val("change")
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#fotoKtp').attr('src', e.target.result); // Set src attribute with selected image data
    };
    reader.readAsDataURL(input.files[0]);
  }
});
$('#inputFoto').change(function() {
  var input = this;
  $('#fotoStatus').val("change")
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#karyawanFoto').attr('src', e.target.result); // Set src attribute with selected image data
    };
    reader.readAsDataURL(input.files[0]);
  }
});
$('#hapusKTPBtn').click(function() {
  $('#fotoKtp').attr('src', ''); // Clears the src attribute
  $('#fotoKtpStatus').val("delete")
});
