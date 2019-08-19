//move to edit employee information
function EditEmployee(Component){
    console.log("sth");
}

//Ban employee
function BanEmployee(Component){
    var id=Component.parentNode.className;
    console.log(id);
    window.open("/ban_employee?employeeid="+id,"_self");
}

//delete an employee
function DeleteEmployee(Component){
    console.log("sth");
}