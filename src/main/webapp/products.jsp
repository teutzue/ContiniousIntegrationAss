<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, entity.Bottom, entity.Top, entity.User, entity.Invoice, entity.InvoiceLine"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cupcake</title>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <div>
            <%
                Object o = session.getAttribute("user");
                User user = new User("Anonymous", "none", 0);
                
                if (o != null)
                {
                    user = (User) o;
                }
            %>
            <p>Logged in as: <%= user.getUserName()%></p>
            <p>Account: <%= user.getBalance()%></p>
        </div>

        <h2>Compose cupcakes from 1 bottom and 1 top and add to shopping cart</h2>
        
        <form id="addProduct" action="Control" method="POST">
            <input type="hidden" name="origin" value="addProduct">
            <table>
                <thead>
                    <tr>
                        <th>Bottom</th>
                        <th>Topping</th>
                        <th>Quantity</th>
                        <th>Select</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select name="bottom">
                                <option value="0">Choose bottom</option>
                                <%
                                    List<Bottom> bottoms = (List<Bottom>) session.getAttribute("bottoms");
                                    
                                    for (Bottom bottom : bottoms)
                                    {
                                        out.print("<option value=\"" + bottom.getId() + "\">" + bottom.getName() + ": " + bottom.getPrice() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                        <td>
                            <select name="topping">
                                <option value="0">Choose topping</option>
                                <%
                                    List<Top> tops = (List<Top>) session.getAttribute("toppings");
                                    
                                    for (Top topping : tops)
                                    {
                                        out.print("<option value=\"" + topping.getId() + "\">" + topping.getName() + ": " + topping.getPrice() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="qty" placeholder="quantity">
                        </td>
                        <td>
                            <input type="submit" name="submit" value="Order">
                        </td>
                    </tr>
                </tbody>
            </table>
                                           
            
            
        </form>
                            
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (session.getAttribute("invoice") != null)
                    {
                        Invoice invoice = (Invoice) session.getAttribute("invoice");
                        List<InvoiceLine> lines = invoice.getLines();
                        for (int i = 0; i < lines.size(); i++)
                        {
                            InvoiceLine line = lines.get(i);
                            out.print("<tr><td>" + line.getCupcake().getName() + "</td><td>" + line.getAmount() + "</td><td class=\"lineprice\">" + line.getPrice() + "</td></tr>");
                        }
                        out.print("<tr><td><h3>Total price</h3></td><td></td><td id=\"totalprice\"></td></tr>");
                        //lines.forEach((x)-> {out.print(x.getCupcake().getCakeName()));
                    }
                %>
            </tbody>
        </table>

        <script src="scripts/javascript.js" type="text/javascript"></script>

    </body>
</html>
