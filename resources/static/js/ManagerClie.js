
//Permite editar la info del cliente
function EditClient(Component){
    //Declaracion de variables
    var idCliente=Component.parentNode.className;
    window.open("/edit_client?clientid="+idCliente,"_self");
}

//Banea el cliente
function BanClient(Component){
    swal({
          title: "Esta seguro?",
          text: "Luego de banear un cliente solo un administrador puede recuperarlo.",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            swal("Cliente baneado!", {
              icon: "warning",
            });
            setTimeout(function(){ window.open("/ban_client?clientid="+idCliente,"_self"); }, 2000);

          } else {
            swal("Accion Revertida!", {
              icon: "success",
            });
          }
        });
}

//Elimina un cliente
function DeleteClient(Component){
    swal({
      title: "Esta seguro?",
      text: "Luego de borrar un cliente solo un administrador puede recuperarlo.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        swal("Cliente eliminado!", {
          icon: "success",
        });
        setTimeout(function(){ window.open("/delete_client?clientid="+idCliente,"_self"); }, 1000);
      } else {
        swal("Accion Revertida!", {
            icon: "success",
        });
      }
    });
}
//Te devuelve al menu cliente
function ReturnToMenuClient(){
    document.getElementById("btnLimpiar").click();
    window.open("/manage_clients","_self");
}