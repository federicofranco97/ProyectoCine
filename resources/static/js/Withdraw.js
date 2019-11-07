//Declaracion de variables
var inTotal = document.getElementById("totalSales");
var amountTaken = document.getElementById("amountTaken");
var amountSelected = parseInt(amountTaken.value);
var totalSales = parseInt(inTotal.value);
var withdrawDone = 0; 
var withdrawDoneSent = document.getElementById("withdrawDoneSent");
//Fin declaración de variables

//Verifico que el valor ingresado para retirar sea valido, e igual o mejor que el total
//vendido.
function VerifyWithdraw(){
    amountSelected = parseInt(amountTaken.value);
    totalSales = parseInt(inTotal.value);
    if(amountSelected < 1 || amountSelected > totalSales || amountSelected === null || Number.isNaN(amountSelected)){
        amountSelected.value = "";
        return false;
    }
    return true;
}

//Pide la credencial de un supervisor.
function AskForId(){
    if(VerifyWithdraw()){
    	totalSales = parseInt(inTotal.value);
    	amountSelected = parseInt(amountTaken.value);
    	withdrawDone = totalSales - amountSelected;
    	withdrawDoneSent.value = withdrawDone;
        ShowSimpleMessage('Por favor ingrese credenciales del supervisor!');
        form.style.display = "block";
        //pedir credenciales
    }else{
          ShowSimpleMessage('Monto ingresado invalido!');
    }
}

//Muestra un mensaje tipo pop up.
function ShowSimpleMessage(text){
	swal(text);
}