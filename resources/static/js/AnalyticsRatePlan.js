//Defino elelemento tarifa sugerida
var rateSuggested = document.getElementById("suggested");

//Funcion para enviar a pagina de votacione en caso de aceptar
function sendRate(){
	swal({
        title: "Aviso",
        text: "Desea seleccionar esta tarifa?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((selectionRate) => {
        if (selectionRate) {
          swal("Tarifa "+ rateSuggested.innerText +" seleccionada!", {
            icon: "warning",
          });
          setTimeout("MoveToVote()", 2000);
        } else {
        	
        }
      });
}

//Funcion que redirecciona a pagina de votacion
function MoveToVote(){
	window.open("/votacion_page?suggested="+parseInt(rateSuggested.innerText),"_self");
}