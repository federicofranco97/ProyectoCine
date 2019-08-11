


function VerifyWithdraw(){
    if(Amount < 1  || Amount>TotalSales || Amount===null || Number.isNaN(Amount)){
        document.getElementById("inputAlivio").value="";
    }else{
    	WithdrawAmount=true;
    }
}

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
                swal(`Id ingresado no valido.`);
            }
        });
    }else{
          swal(`Monto ingresado invalido`);
    }
}

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
                swal(`Clave ingresado no valido.`);
            }
        });
    }else{
          swal(`Monto ingresado invalido`);
    }
}

function AskForCredentials(){
    if(IdValid && PwdValid){
        swal(`Alivio registrado con exito!`);
        inTotal.value=TotalSales-AmountSelected;
        inAmount.value="";
        //Vuelvo los valores a defecto para que el siguiente login haga la validacion
        IdValid=false;
        PwdValid=false;
    }
}

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