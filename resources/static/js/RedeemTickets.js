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
                  text: "Pedido enviado",
                  icon: "success",
                  buttons: true

                })
                setTimeout(BringBooking,1500);
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
    window.sessionStorage.setItem('codeForRedeem',idCode);
    window.open("/buscar_reserva?bookingId="+idCode,"_self");
    console.log(idCode);
}
function ReedemBooking(){
    var idCode = window.sessionStorage.getItem('codeForRedeem');
    window.open("/imprimir_reserva?bookingId="+idCode,"_self");
}



