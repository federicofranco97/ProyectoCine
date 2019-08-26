//move to edit client information
function EditClient(Component){
    var id=Component.parentNode.className;
    window.open("/edit_client?clientid="+id,"_self");
}

//Ban client
function BanClient(Component){
    var id=Component.parentNode.className;
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
            setTimeout(function(){ window.open("/ban_client?clientid="+id,"_self"); }, 2000);

          } else {
            swal("Accion Revertida!", {
              icon: "success",
            });
          }
        });
}

//delete a client
function DeleteClient(Component){
    var id=Component.parentNode.className;
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
        setTimeout(function(){ window.open("/delete_client?clientid="+id,"_self"); }, 1000);
      } else {
        swal("Accion Revertida!", {
            icon: "success",
        });
      }
    });
}

function ReturnToMenuClient(){
    document.getElementById('btnLimpiar').click();
    window.open("/manage_clients","_self");
}