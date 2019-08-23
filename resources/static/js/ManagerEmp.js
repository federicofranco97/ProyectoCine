//move to edit employee information
function EditEmployee(Component){
    var id=Component.parentNode.className;
    window.open("/edit_employee?employeeid="+id,"_self");
}

//Ban employee
function BanEmployee(Component){
    var id=Component.parentNode.className;
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
            setTimeout(function(){ window.open("/ban_employee?employeeid="+id,"_self"); }, 2000);

          } else {
            swal("Accion Revertida!", {
              icon: "success",
            });
          }
        });
}

//delete an employee
function DeleteEmployee(Component){
    var id=Component.parentNode.className;
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
        setTimeout(function(){ window.open("/delete_employee?employeeid="+id,"_self"); }, 2000);
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