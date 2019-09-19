//Declaracion de variables
var withdrawAmount = false;
var idValid = false;
var pwdValid = false;
var id = "admin";
var pwd = "admin";
var inAmount = document.getElementById("amountTaken");
var inTotal = document.getElementById("totalSales");
var amountSelected = parseInt(inAmount.value);
var totalSales = parseInt(inTotal.value);
//Fin declaración de variables

//Verifico que el valor ingresado para retirar sea valido, e igual o mejor que el total
//vendido.
function VerifyWithdraw(){

    if(amountSelected < 1 || amountSelected > totalSales || amountSelected===null || Number.isNaN(amountSelected)){
        inAmount.value = "";
        withdrawAmount = false;
    }else{
    	withdrawAmount = true;
    }
}
//Pide la credencial de un supervisor.
function AskForId(){
	VerifyWithdraw();
    if(withdrawAmount){
        swal('Ingrese su id de Supervisor:', {
            content: 'input'
        })
        .then((value) => {
            if(value===id){
                idValid = true;
                AskForPwd();
            }else{
            	ShowSimpleMessage('id ingresado invalido!');
            }
        });
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
        inAmount.value = "";
        //Vuelvo los valores a defecto para que el siguiente login haga la validacion
        idValid=false;
        pwdValid=false;
    }
}

//Muestra un mensaje tipo pop up.
function ShowSimpleMessage(text){
	swal(text);
}