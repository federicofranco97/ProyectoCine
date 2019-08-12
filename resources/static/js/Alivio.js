//Variable Declaration
var WithdrawAmount =false;
var IdValid =false;
var PwdValid =false;
var Id="admin";
var Pwd="admin";
var AmountSelected=parseInt(document.getElementById("amountTaken").value);
var TotalSales= parseInt(document.getElementById("totalSales").value);
var inAmount=document.getElementById("amountTaken");
var inTotal=document.getElementById("totalSales");
//End Variable declaration

//Verifico que el valor ingresado para retirar sea valido, e igual o mehor que el total
//vendido.
function VerifyWithdraw(){
    if(Amount < 1  || Amount>TotalSales || Amount===null || Number.isNaN(Amount)){
        document.getElementById("inputAlivio").value="";
    }else{
    	WithdrawAmount=true;
    }
}

//Pide la credencial de un supervisor.
function AskForID(){
	VerifyWithdraw();
    console.log(WithdrawAmount);
    if(WithdrawAmount){
        swal("Ingrese su id de Supervisor:", {
            content: "input"
        })
        .then((value) => {
            if(value===Id){
                IdValid=true;
                AskForPWD();
            }else{
            	ShowSimpleMessage('ID ingresado invalido!');
            }
        });
    }else{
          ShowSimpleMessage('Monto ingresado invalido!');
    }
}

//Pide la calve de un supervisor. 
function AskForPWD(){
    if(WithdrawAmount){
        swal("Ingrese su clave de Supervisor:", {
            content: "input"
        })
        .then((value) => {
            if(value===Pwd){
                PwdValid=true;
                console.log(PwdValid);
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
    if(IdValid && PwdValid){
        ShowSimpleMessage('Alivio registrado con exito!');
        inTotal.value=TotalSales-AmountSelected;
        inAmount.value="";
        //Vuelvo los valores a defecto para que el siguiente login haga la validacion
        IdValid=false;
        PwdValid=false;
    }
}

//Muestra un mensaje tipo pop up.
function ShowSimpleMessage(text){
	swal(text);
}