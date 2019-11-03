function SendMoney(){
    var amount1 = document.getElementById("inCash").value;
    var amount2 = document.getElementById("amountTotal").innerText;
    if(amount1 === amount2)
    {
        swal({
                          title: "Exito",
                          text: "Venta efectuada con éxito!",
                          icon: "success",
                          buttons: true
                        });
    }

}
function autoSub(){
    var amount1= document.getElementById("inCash").value;
    var amount2= document.getElementById("amountTotal").innerText;
    var change= document.getElementById("change");
    var changeResult= (amount1-amount2);
    change.disabled="false";
    change.value= changeResult;
}