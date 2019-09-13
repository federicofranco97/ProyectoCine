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
document.getElementById("idCode").addEventListener("keydown", function(event) {
  if (event.keyCode === 13) {
   document.getElementById("searchCode").click();
  }
});