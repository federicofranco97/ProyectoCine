//Declaracion de variables
var withdrawAmount = false;
var idValid = false;
var pwdValid = false;
var id = "admin";
var pwd = "admin";
var inTotal = document.getElementById("totalSales");
var amountSelected = parseInt(amountTaken.value);
var totalSales = parseInt(inTotal.value);
//Fin declaración de variables

//Verifico que el valor ingresado para retirar sea valido, e igual o mejor que el total
//vendido.

function VerifyWithdraw(){
    amountSelected = parseInt(amountTaken.value);
    totalSales = parseInt(inTotal.value);
    if(amountSelected < 1 || amountSelected > totalSales || amountSelected===null || Number.isNaN(amountSelected)){
        amountSelected.value = "";
        withdrawAmount = false;
    }else{
    	withdrawAmount = true;
    }
}
//Pide la credencial de un supervisor.
function AskForId(){
	VerifyWithdraw();
    if(withdrawAmount){
        ShowSimpleMessage('Por favor ingrese sus credenciales!');
        //pedir credenciales
    }else{
          ShowSimpleMessage('Monto ingresado invalido!');
    }
}

//Pide la clave de un supervisor. 
function AskForPwd(){
    if(withdrawAmount){
        swal('Ingrese su clave de Supervisor:', {
            content: 'input'
        })
        .then((value) => {
            if(value===pwd){
                pwdValid=true;
                console.log(pwdValid);
                AskForCredentials();
            }else{
                ShowSimpleMessage('Clave ingresado no valido.');
            }
        });
    }else{
          ShowSimpleMessage('Monto ingresado no valido!');
    }
}

//Checkea que ambas credenciales esten validas, y realiza el alivio de caja.
function AskForCredentials(){
    if(idValid && pwdValid){
        ShowSimpleMessage('Alivio registrado con exito!');
        inTotal.value = totalSales-amountSelected;
        amountSelected.value = "";
        //Vuelvo los valores a defecto para que el siguiente login haga la validacion
        idValid=false;
        pwdValid=false;
    }
}

//Muestra un mensaje tipo pop up.
function ShowSimpleMessage(text){
	swal(text);
}