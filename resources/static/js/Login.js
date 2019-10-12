// Se definio la variable asignandole el tag por ID del formulario
var formEmployee = document.getElementById("formPass");
// Se definieron variables de los campos pass y Id
var password = document.getElementById("EmployeeId");
var idEmployee = document.getElementById("EmployeePass");
// Variable que va a guardar el body por id
var body = document.getElementById("body");
// Regex para campos vacios
var whiteSpace = /^\s+$/;
/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton submit del formulario
 * 
 * @param  {event}
 */
formEmployee.onsubmit = function(event){
	/*
	   Recorre los elementos del formulario y verifica por cada elemento del mismo
	   que no este vacio ni con espacios en blanco, previniendo la accion por default del formulario
	   y mostrando el aviso apropiado
	*/
	for (var i = 0; i < formEmployee.length; i++){
		if(formEmployee.elements[i].value == "" || whiteSpace.test(formEmployee.elements[i].value)){
			event.preventDefault();
			swal("Aviso", "Verifique los campos!", "warning");
		}
	}
	sessionStorage.setItem("Employeekey", idEmployee);
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando cargue la pagina.
 * Busca si se encuentra guardad la variable de sesion de empleado y de ser asi la borra.
 */
body.onload = function(){
	if(sessionStorage.getItem("EmployeeKey")){
		sessionStorage.removeItem("EmployeeKey");
	}
}
// Funcion que produce una tooltip en cada campo, recibiendo el id del tag y el mensaje que queremos mostrar
tippy("#EmployeeId", {content: "Campo requerido"});
tippy("#EmployeePass", {content: "Campo requerido"});