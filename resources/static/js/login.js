var formEmployee = document.getElementById("formPass");
var pasword = document.getElementById("EmployeeId");
var id = document.getElementById("EmployeePass");
var whiteSpace = /^\s+$/;

formEmployee.onsubmit = function(event){
	for (var i = 0; i < formEmployee.length; i++) 
	{
		if(formEmployee.elements[i].value == "" || whiteSpace.test(formEmployee.elements[i].value))
		{
			event.preventDefault();
			swal("Aviso", "Verifique los campos!", "warning");
		}
	}
	
}
// Funcion que produce una tooltip en cada campo, recibiendo el id del tag y el mensaje que queremos mostrar
tippy('#EmployeeId', {content: 'Campo requerido'});
tippy('#EmployeePass', {content: 'Campo requerido'});