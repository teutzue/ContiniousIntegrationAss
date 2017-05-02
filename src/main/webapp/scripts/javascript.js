var totalElement = document.getElementById("totalprice");
var pricearr = document.getElementsByClassName("lineprice");
var totalprice = 0;

for (var i = 0; i < pricearr.length; i++)
{
    var priceText = pricearr[i].textContent;
    var price = Number(priceText);
    
    totalprice += price;
}

totalElement.innerHTML = "<h3>" + totalprice + "</h3>";