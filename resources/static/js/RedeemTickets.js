function Validate(){
    var idCode = document.getElementById("idCode").value;
    console.log(idCode);
    if(idCode === "" || idCode === "  "){
        swal({
                  title: "Error",
                  text: "No ingreso un codigo valido",
                  icon: "warning",
                  buttons: true

                })

    }else{
        swal({
                  title: "Exito",
                  text: "Codigo correcto",
                  icon: "success",
                  buttons: true

                })
    }

}
var idCode = document.getElementById("idCode").value;
idCode.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("searchCode").click();
  }
});