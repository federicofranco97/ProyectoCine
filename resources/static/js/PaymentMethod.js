function SendMoney(){
    var amount1 = document.getElementById("inCash").value;
    var amount2 = document.getElementById("amountTotal").innerText;
    var payMethod = document.getElementById("payMethod").value;
    if(amount1 === amount2)
    {
        swal({
                          title: "Exito",
                          text: "Venta efectuada con éxito!",
                          icon: "success",
                          buttons: true
                        });
        window.open("/metodo_pago?payParam="+payMethod,"_self");
        setTimeout("returnToMenu()",1500);

    }

}

function returnToMenu(){
    window.open("/menu","_self");
}

function autoSub(){
    var amount1= document.getElementById("inCash").value;
    var amount2= document.getElementById("amountTotal").innerText;
    var change= document.getElementById("change");
    var changeResult= (amount1-amount2);
    change.disabled="false";
    change.value= changeResult;
}

function cashValue(){
    var moneyVal = document.getElementById("payMethod");
    var cash = document.getElementById("cashPay");
    if(cash.onclick){
        moneyVal.value = 1;
     }
 }

function creditValue(){
    var moneyVal = document.getElementById("payMethod");
    var credit = document.getElementById("ml");
    if(credit.onclick){
        moneyVal.value = 2;
     }
     window.open("/mercado_pago","_blank");
}