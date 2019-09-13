
//Te lleva a editar la info del empleado
function EditEmployee(Component){
    var idEmployee = Component.parentNode.className;
    window.open("/edit_employee?employeeid="+idEmployee,"_self");
}

function GetReport(Component){
    var idEmployee = Component.parentNode.className;
    window.open("/get_report?employeeid="+idEmployee,"_self");
}

//Banea empleado
function BanEmployee(Component){
    swal({
          title: "Esta seguro?",
          text: "Luego de banear un empleado solo un administrador puede recuperarlo.",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            swal("Empleado baneado!", {
              icon: "warning",
            });
            setTimeout(function(){ window.open("/ban_employee?employeeid="+idEmployee,"_self"); }, 2000);

          } else {
            swal("Accion Revertida!", {
              icon: "success",
            });
          }
        });
}

//Elimina un empleado
function DeleteEmployee(Component){
    swal({
      title: "Esta seguro?",
      text: "Luego de borrar un empleado solo un administrador puede recuperarlo.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        swal("Empleado eliminado!", {
          icon: "success",
        });
        setTimeout(function(){ window.open("/delete_employee?employeeid="+idEmployee,"_self"); }, 2000);
      } else {
        swal("Accion Revertida!", {
            icon: "success",
        });
      }
    });
}

function ReturnToMenu(){
    document.getElementById('btnLimpiar').click();
    window.open("/manage_employees","_self");
}