var p =false;
var IdValid =false;
var PwdValid =false;
var Id="admin";
var Pwd="admin";


function ValidarAlivio(){
    var importe= parseInt(document.getElementById("inputAlivio").value);
    var vendido= parseInt(document.getElementById("TotalVendido").value);
    
    if(importe < 1  || importe>vendido || importe===null || Number.isNaN(importe)){
        document.getElementById("inputAlivio").value="";
    }else{
        p=true;
    }
}

function AskForID(){
    ValidarAlivio();
    console.log(p);
    if(p){
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
    if(p){
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
        var importe= parseInt(document.getElementById("inputAlivio").value);
        var vendido= parseInt(document.getElementById("TotalVendido").value);
        document.getElementById("TotalVendido").value=vendido-importe;
        document.getElementById("inputAlivio").value="";
        //Vuelvo los valores a defecto para que el siguiente login haga la validacion
        IdValid=false;
        PwdValid=false;
    }
}