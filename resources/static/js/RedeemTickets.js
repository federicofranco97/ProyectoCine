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
                BringBooking();
    }

}
document.getElementById("idCode").addEventListener("keydown", function(event) {
  if (event.keyCode === 13) {
   document.getElementById("searchCode").click();
  }
});

//funcion que trae el codigo de reserva
function BringBooking(){
    var idCode = document.getElementById("idCode").value;
    window.open("/buscar_reserva?bookingId="+idCode,"_self");
    console.log(idCode);
}



