//Variables de incremento de cada categoria
var underAgeIncrement = 0;
var retiredIncrement = 0;
var adultIncrement = 0;
var promoIncrement = 0;
var registeredAdultIncrement = 0;
var registeredUnderAgeIncrement = 0;
var registeredOlderIncrement = 0;
//Variable que guarda el total del valor de entradas
var total = 0;
//Variable que guarda las cantidades de entradas por categoria para enviar al backend
var sent = "";
//Variables que traen el elemento correspondiente a su id para utilizar sus valores
var totalPay = document.getElementById("pesos");
var underAge = document.getElementById("Menor");
var retired = document.getElementById("Jubilado");
var adult = document.getElementById("Adulto");
var promo = document.getElementById("Promo2x1");
var registeredAdult = document.getElementById("RegistradoAdulto");
var registeredUnderAge = document.getElementById("RegistradoMenor");
var registeredOlder = document.getElementById("RegistradoMayor");
//Variables que traen los elementos input correspondientes a su id que van a 
//venir del back por medio de thyemeleaf y recibiran los precios de cada
//categoria para calcularlo, ya que no se puede obtener el value de un elemento td
var inputUnderAge = document.getElementById("inputMenor");
var inputRetired = document.getElementById("inputJubilado");
var inputAdult = document.getElementById("inputAdulto");
var inputPromo = document.getElementById("inputPromo2x1");
var inputRegisteredAdult = document.getElementById("inputRegistradoAdulto");
var inputRegisteredUnderAge = document.getElementById("inputRegistradoMenor");
var inputRegisteredOlder = document.getElementById("inputRegistradoMayor");
//Variables que guarda el elemento formulario
var formClient = document.getElementById("formClient");
//Variable que guarda el valor del elemento input del cliente 
var inputIdClient = document.getElementById("inputIdClient");
//Variable que guarda el elemento del formulario de registro del cliente
var formRegisterClient = document.getElementById("formRegisterClient");
//Variables que guardan los elementos input del formulario de registro del cliente
//para hacer las validaciones correspondientes
var completeName = document.getElementById("completeName");
var email = document.getElementById("email");
var date = document.getElementById("date");
var dni = document.getElementById("dni");
//Trae el elemento del boton para cerrar el formulario
var closeFormBtn = document.getElementById("closeFormBtn");
//Trae el div contenedor de enetradas
var container_tables = document.getElementById("container_tables");
//Trae el elemento que contiene el panel de botones
var panel = document.getElementById("panel");
//Regex para validar que no haya espacios vacios en los campos del formulario
var whiteSpace = /^\s+$/;


/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para sumar
 * entradas.
 * Rercibe como parametro el nombre de la categoria traido desde el backend y compara
 * para saber en que categoria debe agregar entradas, sumarlo al total y mostrarlo,
 * tanto en total como en su respectiva categoria
 * @param  {element}
 */
function add(element){
	if (element == 'Menor') {
		underAgeIncrement ++;
		total = total + parseInt(inputUnderAge.value);
		totalPay.innerHTML = "$" + total;
		underAge.innerHTML = underAgeIncrement;
	}
	if (element == 'Jubilado') {
		retiredIncrement ++;
		total = total + parseInt(inputRetired.value);
		totalPay.innerHTML = "$" + total;
		retired.innerHTML = retiredIncrement;
	}
	if (element == 'Adulto') {
		adultIncrement ++;
		total = total + parseInt(inputAdulto.value);
		totalPay.innerHTML = "$" + total;
		adult.innerHTML = adultIncrement;
	}
	if (element == 'Promo2x1') {
		promoIncrement ++;
		total = total + parseInt(inputPromo.value);
		totalPay.innerHTML = "$" + total;
		promo.innerHTML =  promoIncrement;
	}
	if (element == 'RegistradoAdulto') {
		registeredAdultIncrement ++;
		total = total + parseInt(inputRegisteredAdult.value);
		totalPay.innerHTML = "$" + total;
		registeredAdult.innerHTML = registeredAdultIncrement;
	}
	if (element == 'RegistradoMenor') {
		registeredUnderAgeIncrement ++;
		total = total + parseInt(inputRegisteredUnderAge.value);
		totalPay.innerHTML = "$" + total;
		registeredUnderAge.innerHTML = registeredUnderAgeIncrement;
	}
	if (element == 'RegistradoMayor') {
		registeredOlderIncrement ++;
		total = total + parseInt(inputRegisteredOlder.value);
		totalPay.innerHTML = "$" + total;
		registeredOlder.innerHTML = registeredOlderIncrement;
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para restar
 * entradas.
 * Rercibe como parametro el nombre de la categoria traido desde el backend y compara
 * para saber en que categoria debe sacar entradas, reflejarlo en el total y mostrarlo,
 * tanto en total como en su respectiva categoria
 * @param  {element}
 */
function minus(element){
	if (element == 'Menor' &&  underAgeIncrement != 0) {
		underAgeIncrement --;
		total = total - parseInt(inputUnderAge.value);
		totalPay.innerHTML = "$" + total;
		underAge.innerHTML = underAgeIncrement;
	}
	if (element == 'Jubilado' && retiredIncrement != 0) {
		retiredIncrement --;
		total = total - parseInt(inputRetired.value);
		totalPay.innerHTML = "$" + total;
		retired.innerHTML = retiredIncrement;
	}
	if (element == 'Adulto' && adultIncrement != 0) {
		adultIncrement --;
		total = total - parseInt(inputAdult.value);
		totalPay.innerHTML = "$" + total;
		adult.innerHTML = adultIncrement;
	}
	if (element == 'Promo2x1' && promoIncrement != 0) {
		promoIncrement --;
		total = total - parseInt(inputPromo.value);
		totalPay.innerHTML = "$" + total;
		promo.innerHTML = promoIncrement;
	}
	if (element == 'RegistradoAdulto' && registeredAdultIncrement != 0) {
		registeredAdultIncrement --;
		total = total - parseInt(inputRegisteredAdult.value);
		totalPay.innerHTML = "$" + total;
		registeredAdult.innerHTML = registeredAdultIncrement;
	}
	if (element == 'RegistradoMenor' && registeredUnderAgeIncrement != 0) {
		registeredUnderAgeIncrement --;
		total = total - parseInt(inputRegisteredUnderAge.value);
		totalPay.innerHTML = "$" + total;
		registeredUnderAge.innerHTML = registeredUnderAgeIncrement;
	}
	if (element == 'RegistradoMayor' && registeredOlderIncrement != 0) {
		registeredOlderIncrement --;
		total = total - parseInt(inputRegisteredOlder.value);
		totalPay.innerHTML = "$" + total;
		registeredOlder.innerHTML = registeredOlderIncrement;
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para ir a la 
 * pagina de pago.
 * Valida que aunque sea se haya agregado una entrada de alguna categoria, de lo contrario
 * no deja continuar dando un aviso.
 */
function pay(){
	if (total == 0) {
		swal("Aviso", "No hay entradas seleccionadas!", "warning");
	}
	else {
		sent = String(underAgeIncrement)+"_"+String(retiredIncrement)+"_"+String(adultIncrement)+"_"+String(promoIncrement)+"_"+String(registeredAdultIncrement)+"_"+String(registeredUnderAgeIncrement)+"_"+String(registeredOlderIncrement);
		window.open("/presencial_pagar?underAge="+underAgeIncrement+"&retired="+retiredIncrement+"&adult="+adultIncrement+"&promo="+promoIncrement+"&registeredAdult="+registeredAdultIncrement+"&registeredUnderAge="+registeredUnderAgeIncrement+"&registeredOlder="+registeredOlderIncrement+"&total="+total,"_self");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para volver 
 * a la pagina de seleccion de pelicula y funcion.
 */
function back(){
	window.open("/venta_presencial?cantidades="+sent,"_self");
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton de submit
 * de ingreso del cliente, validando el input id.
 * @param {event}
 */
formClient.onsubmit = function(event){
	if (formClient.elements[0].value == "" || whiteSpace.test(formClient.elements[0].value)) {
		event.preventDefault();
	    swal("Aviso", "Asegurese de ingresar un id Cliente antes de continuar!", "warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton de submit
 * del formulario de registro de cliente, validando sus campos.
 * @param {event}
 */
formRegisterClient.onsubmit = function(event){
	if (completeName.value == "" || whiteSpace.test(completeName.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los campos!", "warning");
	}
	if (dni.value == "" || whiteSpace.test(dni.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los campos!", "warning");
	}
	if (date.value == "" || whiteSpace.test(date.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique!", "warning");
	}
	if (email.value == "" || whiteSpace.test(email.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los capos!", "warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton para
 * cerrar el formulario de registro de clientes, cerrando el formulario y 
 * activando la pagina principal.
 * @param {event}
 */
closeFormBtn.onclick = function(event){
	event.preventDefault();
	formRegisterClient.style.display = "none";
	formClient.style.opacity = "1"
	formClient.style.pointerEvents = "auto";
	panel.style.opacity = "1";
	panel.style.pointerEvents = "auto";
	container_tables.style.opacity = "1";
	container_tables.style.pointerEvents = "auto";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton para
 * tener acceso al formulario de registro de clientes, abriendo el formulario y 
 * desactivando la pagina principal.
 * @param {event}
 */
function registerClient(){
	formRegisterClient.style.display = "block";
	formClient.style.opacity = "0.3";
	formClient.style.pointerEvents = "none";
	panel.style.opacity = "0.3";
	panel.style.pointerEvents = "none";
	container_tables.style.opacity = "0.3";
	container_tables.style.pointerEvents = "none";
}

//Funcion reservada para asociar tooltips a determinados campos
//trayendolos por id y popniendole un mensaje a el campo
tippy('#name',{content: 'Campo requerido'});
tippy('#date',{content: 'Campo requerido'});
tippy('#email',{content: 'Campo requerido'});
tippy('#dni',{content: 'Campo requerido'});